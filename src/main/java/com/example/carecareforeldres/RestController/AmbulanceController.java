package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Dto.AmbulanceDto;
import com.example.carecareforeldres.Dto.EtablissementDto;
import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Service.IServiceAmbulance;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ambulance")
@CrossOrigin("*")

public class AmbulanceController {
    IServiceAmbulance serviceAmbulance;
    @GetMapping("/retrieve-all-ambulance")

    /*public List<Ambulance> getAmbulancess() {
        List<Ambulance> ambulances = serviceAmbulance.retrieveAllAmbulances();
        return ambulances;*/
    public List<AmbulanceDto> getAmbulancess() {
        List<Ambulance> ambulances = serviceAmbulance.retrieveAllAmbulances();
        List<AmbulanceDto>list=ambulances.stream().map(ambulance ->AmbulanceDto.toDto(ambulance)).toList();
        return list ;
    }

    @GetMapping("/retrieve-ambulance/{ambulance-id}")
   /* public Ambulance retrieveAmbulance(@PathVariable("ambulance-id") Long idAmb) {
        return serviceAmbulance.retrieveAmbulance(idAmb);*/
    public AmbulanceDto retrieveAmbulance(@PathVariable("ambulance-id") Long idAmb) {
        return AmbulanceDto.toDto(serviceAmbulance.retrieveAmbulance(idAmb));




    }
    @PostMapping("/add-ambulance")
    /*public Ambulance addAmbulance(@RequestBody Ambulance ambulance) {
       return serviceAmbulance.addAmbulance(ambulance);*/
        public AmbulanceDto addAmbulance(@RequestBody AmbulanceDto ambulance) {
            Ambulance entity =AmbulanceDto.toEntite(ambulance);
            return AmbulanceDto.toDto(serviceAmbulance.addAmbulance(entity));

    }

    @DeleteMapping("/remove-ambulance/{ambulance-id}")
    public void removeAmbulance(@PathVariable("ambulance-id") Long idAmb) {
        serviceAmbulance.removeAmbulance(idAmb);
    }
    @PutMapping("/update-ambulance")
   /* public Ambulance updateAmbulance(@RequestBody Ambulance ambulance) {
        return serviceAmbulance.updateAmbulance(ambulance);*/
        public AmbulanceDto updateAmbulance(@RequestBody AmbulanceDto ambulance) {
            Ambulance entity=AmbulanceDto.toEntite(ambulance);
            return AmbulanceDto.toDto(serviceAmbulance.updateAmbulance(entity));
    }
    @PutMapping("/ambulance/{idetab}")
    @ResponseBody
    public Ambulance affecterAmbulanceToEtabliss(@RequestBody AmbulanceDto ambulance, @PathVariable("idetab")Long idEtab ) {
        Ambulance entite = AmbulanceDto.toEntite(ambulance);
        return serviceAmbulance.addAmbulanceAndAssignToEtabliss(entite,idEtab);
    }

    @PutMapping("/desaffect/{idamb}")
    @ResponseBody
    public Ambulance DesaffecterAmbulanceToEtabliss(@PathVariable("idamb")Long idamb ) {
        return serviceAmbulance.UnassignAmbulanceEtabliss(idamb);
    }
}
