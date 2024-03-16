package com.example.carecareforeldres.auth;



import com.example.carecareforeldres.Entity.Role;
import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.UserRepository;
import com.example.carecareforeldres.config.JwtService;
import com.example.carecareforeldres.token.Token;
import com.example.carecareforeldres.token.TokenRepository;
import com.example.carecareforeldres.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
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
 @Autowired
  private ObjectMapper objectMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

  public AuthenticationResponse register(User user) {
   /* var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .autority(naa)//autority(na)
        .build();*/
    user.setPasswd( passwordEncoder.encode(user.getPassword()));
    if(user.getRoles()!=null){
      user.getRoles().stream()
              .forEach(obj -> {
                obj.setUserAuth(user);
              });}

    user.setRoles(user.getRoles());
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
// save rox dans la table acteur :inse into
      saveUserToPatientTable(savedUser);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

    private void saveUserToPatientTable(User savedUser) {
        String sql = "INSERT INTO patient (user) VALUES (?)";

        jdbcTemplate.update(sql, savedUser.getId());
    }


    public Authentication authenticate(AuthenticationRequest request) {
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

    List<GrantedAuthority> authorities = getAuthorities(user.getAuthFromBase());
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
     AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
     return new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword(),authorities);
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
 // public User update(User  userr) throws IOException {
   // UpdateRequest user = objectMapper.readValue((DataInput) userr, UpdateRequest.class);//dto

    //User userExicte=repository.findById(user.getId()).get();//real object-->save()
    //userExicte.setEmail(user.getEmail());
    //userExicte.setFirstname(user.getFirstname());
    //userExicte.setLastname(user.getLastname());

    //return repository.save(userExicte);
  //}
}
