package com.example.rendez_vous.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Medecin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMed;
     String nom;
     String mail;
     boolean disponible;
    @Enumerated(EnumType.STRING)
    Specialite specialite;
    //int nbRdvJour;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "medecin",fetch = FetchType.EAGER)
    List<Rdv>rdvs=new ArrayList<>();
}
