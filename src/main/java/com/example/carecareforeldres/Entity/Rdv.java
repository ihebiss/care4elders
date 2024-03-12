package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idRDV;
    LocalDateTime dateRDV;
    boolean enLigne;
    String lienMeet;
    @OneToOne(cascade = CascadeType.ALL)
    Ordannance ordannance;
    @ManyToOne(cascade = CascadeType.ALL)
    User medecin;
    @ManyToOne(cascade = CascadeType.ALL)
    Patient patient;

}
