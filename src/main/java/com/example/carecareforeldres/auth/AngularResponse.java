package com.example.carecareforeldres.auth;

import com.example.carecareforeldres.Entity.User;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AngularResponse {
        private User user;
        private String jwtToken;
}
