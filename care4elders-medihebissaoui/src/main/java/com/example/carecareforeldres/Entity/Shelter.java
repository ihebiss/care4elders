package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Shelter")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shelter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShelter;

    private String nomShelter;

    private Long capaciteShelter;

    private String location;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Aide> aides;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="shelter")
    private List<User> homelesses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="shelter")
    private List<Service> Services;

}
