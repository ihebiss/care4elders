package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Dto.EtablissementDto;
import com.example.carecareforeldres.Dto.MorgueDto;
import com.example.carecareforeldres.Dto.OrdannanceDto;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Entity.Ordannance;
import com.example.carecareforeldres.Service.IServiceOrdonnance;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ordon")
@CrossOrigin("*")
public class OrdonnanceController {
    IServiceOrdonnance serviceOrdonnance;
    @GetMapping("/retrieve-all-ordon")
  /*  public List<Ordannance> getOrdannances() {
        List<Ordannance> ordannances = serviceOrdonnance.retrieveAllOrdannances();
        return ordannances;*/

    public List<OrdannanceDto> getOrdannances() {
        List<Ordannance> ordannances = serviceOrdonnance.retrieveAllOrdannances();
        List<OrdannanceDto>list=ordannances.stream().map(ordannance -> OrdannanceDto.toDto(ordannance)).toList();
        return list;

    }

    @GetMapping("/retrieve-ordon/{ordon-id}")
    /*public Ordannance retrieveOrdannance(@PathVariable("ordon-id") Long idOrd) {
        return serviceOrdonnance.retrieveOrdannance(idOrd);*/
        public OrdannanceDto retrieveOrdannance(@PathVariable("ordon-id") Long idOrd) {
            return OrdannanceDto.toDto(serviceOrdonnance.retrieveOrdannance(idOrd));
        }
    @PostMapping("/add-ordon")
    /*public Ordannance addOrdannance(@RequestBody Ordannance ordannance) {
        return serviceOrdonnance.addOrdannance(ordannance);*/
        public OrdannanceDto addOrdannance(@RequestBody OrdannanceDto ordannance) {
            Ordannance entity=OrdannanceDto.toEntity(ordannance);
            return OrdannanceDto.toDto(serviceOrdonnance.addOrdannance(entity));

    }


    @DeleteMapping("/remove-ordon/{ordon-id}")
    public void removeOrdannance(@PathVariable("ordon-id") Long idOrd) {
        serviceOrdonnance.removeOrdannance(idOrd);
    }
    @PutMapping("/update-ordon")
    /*public Ordannance updateOrdannance(@RequestBody Ordannance ordannance) {
        return serviceOrdonnance.updateOrdannance(ordannance);*/
        public OrdannanceDto updateOrdannance(@RequestBody OrdannanceDto ordannance) {
            Ordannance entity=OrdannanceDto.toEntity(ordannance);
            return OrdannanceDto.toDto(serviceOrdonnance.updateOrdannance(entity));
    }

}
