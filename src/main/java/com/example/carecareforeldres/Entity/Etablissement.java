package com.example.carecareforeldres.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Etablissement implements Serializable {
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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement",fetch = FetchType.EAGER)
    List<Ambulance>ambulances;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    Morgue morgue;
   /* @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Patient> patients;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<User> p_medical;*/

}

