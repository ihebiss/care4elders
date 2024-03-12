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
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idVille;
    String nom;
    @ManyToOne(cascade = CascadeType.ALL)
    Gouvernorat gouvernorat;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "ville")
    List<Localisation>localisations;
}
