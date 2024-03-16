package com.example.carecareforeldres.Dto;

import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorgueDto {
    Long idMorgue;
    Integer nbCadavre;
    /* @OneToOne(cascade = CascadeType.ALL,mappedBy ="morgue")
    Etablissement etablissement;*/
    public static MorgueDto toDto(Morgue Entity){
       return MorgueDto.builder()
                .idMorgue(Entity.getIdMorgue())
                .nbCadavre(Entity.getNbCadavre())
                .build();
    }
    public static Morgue toEntity(MorgueDto Entity){
        return Morgue.builder()
                .idMorgue(Entity.getIdMorgue())
                .nbCadavre(Entity.getNbCadavre())
                .build();
    }
}


