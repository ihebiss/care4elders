package com.example.carecareforeldres.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEtab;
    String nomEtab;
    String numFixe;
    String adresse;
    @Enumerated(EnumType.STRING)
    TypeEtab typeEtab;
    Integer nbLits;
    float prixNuit;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Ambulance>ambulances;
    @OneToOne(cascade = CascadeType.ALL)
    Morgue morgue;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Patient> patients;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<User> p_medical;

}

