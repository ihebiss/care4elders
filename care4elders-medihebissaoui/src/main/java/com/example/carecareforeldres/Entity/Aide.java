package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Aide")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Aide implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAide;

    private float montant;

    private float nbrHeureVolantaire;

    @Enumerated(EnumType.STRING)
    private TypeAide typeAide;



    @ManyToMany(mappedBy="aides", cascade = CascadeType.ALL)
    private List<Shelter> shelters;
}
