package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ambulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAmb;
    String marque;
    String matricule;
    boolean busy;
    @Enumerated(EnumType.STRING)
    EtatAmb etatAmb;
    LocalDate dateDernEntret;
    @OneToOne(cascade = CascadeType.ALL)
    Localisation localisation;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;
}
