package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Dto.EtablissementDto;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Service.IServiceEtablissement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/etab")
@CrossOrigin("*")

public class EtablissementController {
    IServiceEtablissement serviceEtablissement;
    @GetMapping("/retrieve-all-etab")
    public List<EtablissementDto> getEtablissements() {
        List<Etablissement> etablissements = serviceEtablissement.retrieveAllEtablissements();
        List<EtablissementDto> list = etablissements.stream().map(etablissement -> EtablissementDto.toDto(etablissement)).toList();
        return list;
    }

    @GetMapping("/retrieve-etab/{etab-id}")
        public EtablissementDto retrieveEtablissement(@PathVariable("etab-id") Long idEtab) {
        return   EtablissementDto.toDto(serviceEtablissement.retrieveEtablissement(idEtab)) ;


    }

    @PostMapping("/add-etab")
    //public Etablissement addEtablissement(@RequestBody Etablissement etablissement) {
      //  return serviceEtablissement.addEtablissement(etablissement);
    public EtablissementDto addEtablissement(@RequestBody EtablissementDto etablissement) {
        Etablissement entity = EtablissementDto.toEntity(etablissement);
        return EtablissementDto.toDto(serviceEtablissement.addEtablissement(entity));
    }

    @DeleteMapping("/remove-etab/{etab-id}")
    public void removeEtablissement(@PathVariable("etab-id") Long idEtab) {
    serviceEtablissement.removeEtablissement(idEtab);
        }
    @PutMapping("/update-etab")
    /*public Etablissement updateEtablissement(@RequestBody Etablissement etablissement) {
        return serviceEtablissement.updateEtablissement(etablissement);*/
        public EtablissementDto updateEtablissement(@RequestBody EtablissementDto etablissement) {
           Etablissement entity=EtablissementDto.toEntity(etablissement);
            return EtablissementDto.toDto(serviceEtablissement.updateEtablissement(entity));
    }


}
