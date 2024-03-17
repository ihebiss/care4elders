package com.example.rendez_vous.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer idpatient;
     String nom;
     String mail;
    @Enumerated(EnumType.STRING)
     TypePatient typePatient;
     boolean archiver;
     @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
     List<Rdv>rdvs;
}
