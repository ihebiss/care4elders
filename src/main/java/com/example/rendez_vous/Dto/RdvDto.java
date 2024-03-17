package com.example.rendez_vous.Dto;
import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Entity.Patient;
import com.example.rendez_vous.Entity.Rdv;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RdvDto {
    Long idRDV;
    LocalDateTime dateRDV;
    boolean archiver;
    Patient patient;
    Medecin medecin;

    public static RdvDto toDto(Rdv Entity){
        return RdvDto.builder()
                .idRDV(Entity.getIdRDV())
                .dateRDV(Entity.getDateRDV())
                .archiver(Entity.isArchiver())
                .patient(Entity.getPatient())
                .medecin(Entity.getMedecin())
                .build();
    }
    public static Rdv toEntity(RdvDto Entity){
        return Rdv.builder()
                .idRDV(Entity.getIdRDV())
                .dateRDV(Entity.getDateRDV())
                .archiver(Entity.isArchiver())
                .patient(Entity.getPatient())
                .medecin(Entity.getMedecin())
                .build();
    }
}

