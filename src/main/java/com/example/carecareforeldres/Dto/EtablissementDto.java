package com.example.carecareforeldres.Dto;

import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Entity.TypeEtab;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementDto {
    Long idEtab;
    String nomEtab;
    String numFixe;
    String adresse;
    @Enumerated(EnumType.STRING)
    TypeEtab typeEtab;
    Integer nbLits;
    float prixNuit;
   /* @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Ambulance> ambulances;
    @OneToOne(cascade = CascadeType.ALL)
    Morgue morgue;*/
   public static EtablissementDto toDto(Etablissement Entity){
       return EtablissementDto.builder()
               .idEtab(Entity.getIdEtab())
               .adresse(Entity.getAdresse())
               .nbLits(Entity.getNbLits())
               .nomEtab(Entity.getNomEtab())
               .typeEtab(Entity.getTypeEtab())
               .numFixe(Entity.getNumFixe())
               .prixNuit(Entity.getPrixNuit())
               .build();
   }

   }
