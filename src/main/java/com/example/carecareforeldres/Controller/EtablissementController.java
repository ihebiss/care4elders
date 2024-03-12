package com.example.carecareforeldres.Controller;

import com.example.carecareforeldres.Dto.EtablissementDto;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Service.IServiceEtablissement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etab")
public class EtablissementController {
    IServiceEtablissement serviceEtablissement;
    @GetMapping("/retrieve-all-etab")
    public List<Etablissement> getEtablissements() {
        List<Etablissement> etablissements = serviceEtablissement.retrieveAllEtablissements();
        return etablissements;
    }

    @GetMapping("/retrieve-etab/{etab-id}")
    public Etablissement retrieveEtablissement(@PathVariable("etab-id") Long idEtab) {
        return serviceEtablissement.retrieveEtablissement(idEtab);
    }
    @PostMapping("/add-etab")
    public Etablissement addEtablissement(@RequestBody Etablissement etablissement) {
        return serviceEtablissement.addEtablissement(etablissement);
/* public EtablissementDto addEtablissement(@RequestBody Etablissement etablissement) {
        return EtablissementDto.toDto(serviceEtablissement.addEtablissement(etablissement));*/
    }

    @DeleteMapping("/remove-etab/{etab-id}")
    public void removeEtablissement(@PathVariable("etab-id") Long idEtab) {
    serviceEtablissement.removeEtablissement(idEtab);
        }
    @PutMapping("/update-etab")
    public Etablissement updateEtablissement(@RequestBody Etablissement etablissement) {
        return serviceEtablissement.updateEtablissement(etablissement);
    }


}
