package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Evennement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvennement;

    private String nomevennement;

    private String image;



    @Temporal(TemporalType.DATE)
    private Date dateevennement;

    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;

    @ManyToMany(mappedBy = "evennements")
    private List<User> users;

    @ManyToOne
    private Etablissement etablissement;

}
