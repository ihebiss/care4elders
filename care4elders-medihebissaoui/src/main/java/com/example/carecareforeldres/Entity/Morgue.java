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
public class Morgue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idMorgue;
    Integer nbCadavre;
    @OneToOne(cascade = CascadeType.ALL,mappedBy ="morgue")
    Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "morgue")
    List<Patient> patients;
}
