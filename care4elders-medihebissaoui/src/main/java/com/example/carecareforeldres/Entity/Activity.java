package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Activity")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShelter;

    private String nomActivity;

    @Temporal(TemporalType.DATE)
    private Date dateActivity;

    private String heure;
    private String lieu;

    @Enumerated(EnumType.STRING)
    private TypeActivity typeActivity ;




    @OneToMany(cascade = CascadeType.ALL, mappedBy="activity")
    private List<User> Users;


}
