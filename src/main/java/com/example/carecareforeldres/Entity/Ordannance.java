package com.example.carecareforeldres.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Ordannance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idOrd;
     Integer quantite;
     String description;



}
