package com.example.carecareforeldres.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Infermier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInfermier;
    private Boolean disponible;
    private Integer user;
}
