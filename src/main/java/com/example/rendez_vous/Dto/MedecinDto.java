package com.example.rendez_vous.Dto;

import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Entity.Rdv;
import com.example.rendez_vous.Entity.Specialite;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDto {
    Integer idMed;
    String nom;
    String mail;
    //int nbRdvJour;
    boolean disponible;
    @Enumerated(EnumType.STRING)
    Specialite specialite;

    List<Rdv> rdvs=new ArrayList<>();

    public static MedecinDto toDto(Medecin Entity){
        return MedecinDto.builder()
                .idMed(Entity.getIdMed())
                .nom(Entity.getNom())
                .mail(Entity.getMail())
                .specialite(Entity.getSpecialite())
              //  .nbRdvJour(Entity.getNbRdvJour())
                .disponible(Entity.isDisponible())
                .rdvs(Entity.getRdvs())
                .build();
    }
    public static Medecin toEntity(MedecinDto Entity){
        return Medecin.builder()
                .idMed(Entity.getIdMed())
                .nom(Entity.getNom())
                .mail(Entity.getMail())
                .specialite(Entity.getSpecialite())
                // .nbRdvJour(Entity.getNbRdvJour())
                .disponible(Entity.isDisponible())
                .rdvs(Entity.getRdvs())
                .build();
    }

}
