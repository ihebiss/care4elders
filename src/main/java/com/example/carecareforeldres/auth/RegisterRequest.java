package com.example.carecareforeldres.auth;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String region;
  private String numphone;
  private boolean mfaEnabled;
}
