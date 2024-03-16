package com.example.carecareforeldres.auth;


import com.example.carecareforeldres.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody User request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public Authentication authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }
  //@PutMapping(path = "/update")
  //public User update(@RequestBody User user) throws IOException {
    //return service.update(user);
  //}


}
