package com.example.carecareforeldres.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationRequest {
    private String email;
    private String code;
}
