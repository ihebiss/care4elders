package com.example.carecareforeldres.Dto;

import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.EtatAmb;
import com.example.carecareforeldres.Entity.Localisation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmbulanceDto {
    Long idAmb;
    String marque;
    String matricule;
    boolean busy;
    @Enumerated(EnumType.STRING)
    EtatAmb etatAmb;
    LocalDate dateDernEntret;
  /*  @OneToOne(cascade = CascadeType.ALL)
    Localisation localisation;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;*/
    public AmbulanceDto toDto(Ambulance Entity){
        return AmbulanceDto.builder()
                .idAmb(Entity.getIdAmb())
                .busy(Entity.isBusy())
                .marque(Entity.getMarque())
                .matricule(Entity.getMatricule())
                .etatAmb(Entity.getEtatAmb())
                .dateDernEntret(Entity.getDateDernEntret())
                .build();
    }
}
