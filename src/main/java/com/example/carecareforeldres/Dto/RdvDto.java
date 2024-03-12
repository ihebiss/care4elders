package com.example.carecareforeldres.Dto;

import com.example.carecareforeldres.Entity.Ordannance;
import com.example.carecareforeldres.Entity.Rdv;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
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
    boolean enLigne;
    String lienMeet;
    /*@OneToOne(cascade = CascadeType.ALL)
    Ordannance ordannance;*/
    public RdvDto toDto(Rdv Entity){
        return RdvDto.builder()
                .idRDV(Entity.getIdRDV())
                .dateRDV(Entity.getDateRDV())
                .enLigne(Entity.isEnLigne())
                .lienMeet(Entity.getLienMeet())
                .build();
    }

}
