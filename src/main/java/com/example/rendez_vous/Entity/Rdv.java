package com.example.rendez_vous.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Rdv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idRDV;
    LocalDateTime dateRDV;
    boolean archiver;
    @ManyToOne(cascade = CascadeType.ALL)
    Medecin medecin;
    @ManyToOne(cascade = CascadeType.ALL)
    Patient patient;

}
