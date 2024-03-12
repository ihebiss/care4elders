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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idpatient;
    @ManyToOne(cascade = CascadeType.ALL)
    Ambulance ambulance;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="patient")
    List<Rdv> rdvs;
    @ManyToOne(cascade = CascadeType.ALL)
    Morgue morgue;
}
