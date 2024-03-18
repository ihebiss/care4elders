package com.example.carecareforeldres.auth;



import com.example.carecareforeldres.Entity.Role;
import com.example.carecareforeldres.Entity.TypeRole;
import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.RoleRepository;
import com.example.carecareforeldres.Repository.UserRepository;
import com.example.carecareforeldres.config.JwtService;
import com.example.carecareforeldres.tfa.TwoFactorAuthenticationService;
import com.example.carecareforeldres.token.Token;
import com.example.carecareforeldres.token.TokenRepository;
import com.example.carecareforeldres.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.desktop.SystemSleepEvent;
import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor


public class AuthenticationService {
  private final UserRepository repository;


  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final TwoFactorAuthenticationService tfaService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

  public AuthenticationResponse register(User user) {

    user.setPasswd( passwordEncoder.encode(user.getPassword()));
    user.setMfaEnabled(user.isMfaEnabled());
    if(user.getRoles()!=null){
      user.getRoles().stream()
              .forEach(obj -> {
                obj.setUserAuth(user);
              });}

    user.setRoles(user.getRoles());
    user.setNbr_tentatives(0);
    if(user.isMfaEnabled()){user.setSecret(tfaService.generateNewSecret());}
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

// save rox dans la table acteur :inset into
      saveUserToPatientTable(savedUser);
    return AuthenticationResponse.builder()
            .secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))
            .token(jwtToken)
            .mfaEnabled(user.isMfaEnabled())
            .build();
  }

    private void saveUserToPatientTable(User savedUser) {
        {
            for (Role r:savedUser.getRoles()){
                if (r.getName()== TypeRole.PATIENT){
                    String sql = "INSERT INTO patient (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                //{
                  //  for (Role r:savedUser.getRoles()){
                    //    if (r.getName()== TypeRole.PATIENT){
                      //      String sql = "INSERT INTO patient (user,nom,mail) VALUES (?,?,?)";
                        //    jdbcTemplate.update(sql, savedUser.getId(),savedUser.getFirstname(),savedUser.getEmail());

                        //}
                if (r.getName()==TypeRole.MEDECIN){
                    String sql = "INSERT INTO medecin (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                if (r.getName()==TypeRole.AMBILANCIER){
                    String sql = "INSERT INTO ambilancier (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                if (r.getName()==TypeRole.INFERMIER){
                    String sql = "INSERT INTO infermier (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                if (r.getName()==TypeRole.VISITEUR){
                    String sql = "INSERT INTO visiteur (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                if (r.getName()==TypeRole.DONATEUR){
                    String sql = "INSERT INTO donateur (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                if (r.getName()==TypeRole.ORGANISATEUR){
                    String sql = "INSERT INTO organisateur (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }
                if (r.getName()==TypeRole.HOMELESS){
                    String sql = "INSERT INTO homeless (user) VALUES (?)";
                    jdbcTemplate.update(sql, savedUser.getId());
                }

            }}}


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
      User u = repository.fINDMail(request.getEmail());
            if (u != null) {
            if (!passwordEncoder.matches(request.getPassword(), u.getPassword()) && u.getNbr_tentatives() < 3) {
                System.out.println("Wrong password");
                System.out.println(u.getNbr_tentatives());
                u.setNbr_tentatives(u.getNbr_tentatives() + 1);
                repository.save(u);
                System.out.println("Attempt incremented");
                if (u.getNbr_tentatives() >= 3) {
                    System.out.println("User locked");
                    u.setEnabled(true);
                    u.setSleep_time(new Date(System.currentTimeMillis()));
                    repository.save(u);
                }
                return AuthenticationResponse.builder()
                        .error("Wrong password")
                        .build();
            } else if (passwordEncoder.matches(request.getPassword(), u.getPassword()) && u.getNbr_tentatives() < 3) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );
                System.out.println("Authentication successful");
                var user = repository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                System.out.println("User found");

                if (user.isMfaEnabled()) {
                    var jwtToken = jwtService.generateToken(user);
                    revokeAllUserTokens(user);
                    saveUserToken(user, jwtToken);
                    return AuthenticationResponse.builder()
                            .user(user)
                            .token(jwtToken)
                            .mfaEnabled(true)
                            .build();
                }

                var jwtToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, jwtToken);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .user(user)
                        .mfaEnabled(false)
                        .build();
            }
        }

        // Si l'utilisateur n'est pas trouvé ou s'il a dépassé le nombre maximum de tentatives
        return AuthenticationResponse.builder()
                .error("User not found or maximum login attempts reached")
                .build();
    }



    private List<GrantedAuthority> getAuthorities(Set<Role> roleih) {
    List<GrantedAuthority> list = new ArrayList<>();
    for (Role auth : roleih){
      list.add(new SimpleGrantedAuthority(auth.getName().name()));

    }
    return list;
  }
  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)//dely block
        .revoked(false)//tyblikih
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }


    public AuthenticationResponse verifyCode(
            VerificationRequest verificationRequest
    ) {
        User user = repository
                .findByEmail(verificationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user found with %S", verificationRequest.getEmail()))
                );
        if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {

            throw new BadCredentialsException("Code is not correct");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()

                .token(jwtToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }

    public List<User> getConnectedUsersWithRole( String role) {
        List<Integer> tokenMr = tokenRepository.retrieveIdUserConecter();
        List<User> userConnects = new ArrayList<>();
        List<User> userConnectsWithRole = new ArrayList<>();

        // Retrieve connected users
        for (Integer tokenId : tokenMr) {
            userConnects.add(repository.findById(tokenId).get());
            System.out.println("ID USER Connected: " + tokenId);
        }

        // Filter users with the specified role///
        for (User user : userConnects) {
            if (user != null && hasThisRole(user, role)) {
                System.out.println("Connected: " + user.getEmail());
                userConnectsWithRole.add(user);
            }
        }
        return userConnectsWithRole;
    }

    private boolean hasThisRole(User user, String role) {
        for (Role userRole : user.getRoles()) {
            if (userRole != null && userRole.getName() != null && userRole.getName().name().equals(role)) {
                return true;
            }
        }
        return false;
    }


}

