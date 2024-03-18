package com.example.carecareforeldres.auth;

import com.example.carecareforeldres.Entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse {

  private String token;
  private User user;
  private String refreshToken;
  private boolean mfaEnabled;
  private String secretImageUri;
  private String error;
}
