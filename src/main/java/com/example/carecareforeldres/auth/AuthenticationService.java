package com.example.carecareforeldres.auth;



import com.example.carecareforeldres.Entity.Role;
import com.example.carecareforeldres.Entity.TypeRole;
import com.example.carecareforeldres.Entity.User;
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

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

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
  private ObjectMapper objectMapper;
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
    if(user.isMfaEnabled()){user.setSecret(tfaService.generateNewSecret());}
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
// save rox dans la table acteur :inse into
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
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
      System.out.println(" iheb !!");
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("I Cant found this User- "));
    System.out.println("iheb 2!!");

if(user.isMfaEnabled()){
      return   AuthenticationResponse.builder()
              .user(user)
            .token("")
              .mfaEnabled(true)
            .build();
}
    List<GrantedAuthority> authorities = getAuthorities(user.getAuthFromBase());
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
   return   AuthenticationResponse.builder()
        .token(jwtToken)
           .user(user)
           .mfaEnabled(false)
        .build();
    // return new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword(),authorities);
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
    // public void refreshToken(
          //  HttpServletRequest request,
          //  HttpServletResponse response
   // ) throws IOException {
      //  final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
       // final String refreshToken;
       // final String userEmail;
        //if (authHeader == null || !authHeader.startsWith("Bearer ")) {
           // return;
       // }
       // refreshToken = authHeader.substring(7);
        //userEmail = jwtService.extractUsername(refreshToken);
        //if (userEmail != null) {
          //  var user = this.repository.findByEmail(userEmail)
                 //   .orElseThrow();
            //if (jwtService.isTokenValid(refreshToken, user)) {
              //  var accessToken = jwtService.generateToken(user);
                //revokeAllUserTokens(user);
                //saveUserToken(user, accessToken);
                //var authResponse = AuthenticationResponse.builder()
                  //      .token(accessToken)
                    //    .refreshToken(refreshToken)
                      //  .build();
                //new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            //}
        }

