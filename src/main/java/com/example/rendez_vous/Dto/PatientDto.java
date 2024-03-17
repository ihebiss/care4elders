package com.example.rendez_vous.Dto;

import com.example.rendez_vous.Entity.Patient;
import com.example.rendez_vous.Entity.Rdv;
import com.example.rendez_vous.Entity.TypePatient;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    Integer idpatient;
    String nom;
    String mail;
    @Enumerated(EnumType.STRING)
    TypePatient typePatient;
    boolean archiver;
    List<Rdv> rdvs;

    public static PatientDto toDto(Patient Entity){
        return PatientDto.builder()
                .idpatient(Entity.getIdpatient())
                .typePatient(Entity.getTypePatient())
                .nom(Entity.getNom())
                .mail(Entity.getMail())
                .archiver(Entity.isArchiver())
                .rdvs(Entity.getRdvs())
                .build();
    }
    public static Patient toEntity(PatientDto Entity){
        return Patient.builder()
                .idpatient(Entity.getIdpatient())
                .typePatient(Entity.getTypePatient())
                .nom(Entity.getNom())
                .mail(Entity.getMail())
                .archiver(Entity.isArchiver())
                .rdvs(Entity.getRdvs())
                .build();
    }
}
