package com.example.carecareforeldres.auth;


import com.example.carecareforeldres.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;


  @PostMapping("/register")
  public ResponseEntity<?> register(
          @RequestBody User request
  ) {
    var response = service.register(request);
    if (request.isMfaEnabled()) {
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.accepted().build();
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyCode(
          @RequestBody VerificationRequest verificationRequest
  ) {
    return ResponseEntity.ok(service.verifyCode(verificationRequest));
  }


}
