package com.example.carecareforeldres.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ordannance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idOrd;
     Integer quantite;
     String description;



}
