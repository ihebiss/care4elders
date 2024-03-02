package com.example.carecareforeldres.auth;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRequest {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;

}
