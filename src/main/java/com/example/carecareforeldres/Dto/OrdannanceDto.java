package com.example.carecareforeldres.Dto;

import com.example.carecareforeldres.Entity.Ordannance;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdannanceDto {
    Long idOrd;
    Integer quantite;
    String description;
    public OrdannanceDto toDto(Ordannance Entity){
        return OrdannanceDto.builder()
                .idOrd(Entity.getIdOrd())
                .quantite(Entity.getQuantite())
                .description(Entity.getDescription())
                .build();
    }
}
