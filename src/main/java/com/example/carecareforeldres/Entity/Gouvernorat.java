package com.example.carecareforeldres.Entity;
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

public class Gouvernorat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idGouv;
     String nom;
     @OneToMany(cascade = CascadeType.ALL,mappedBy ="gouvernorat" )
     List<Ville>villes;
}
