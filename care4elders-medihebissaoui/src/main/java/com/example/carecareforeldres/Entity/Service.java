package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name = "Service")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;


    @Enumerated(EnumType.STRING)
    private TypeService typeService ;

    @ManyToOne
    Shelter shelter;

}
