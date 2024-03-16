package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Dto.AmbulanceDto;
import com.example.carecareforeldres.Dto.EtablissementDto;
import com.example.carecareforeldres.Dto.MorgueDto;
import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Service.IServiceMorgue;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/morgue")
@CrossOrigin("*")
public class MorgueController {
    IServiceMorgue serviceMorgue;
    @GetMapping("/retrieve-all-morgue")
    /*public List<Morgue> getMorgues() {
        List<Morgue> morgues = serviceMorgue.retrieveAllMorgues();
        return morgues;*/
    public List<MorgueDto> getMorgues() {
        List<Morgue> morgues = serviceMorgue.retrieveAllMorgues();
        List<MorgueDto>list=morgues.stream().map(morgue ->MorgueDto.toDto(morgue)).toList();
        return list ;
    }

    @GetMapping("/retrieve-morgue/{morgue-id}")
   /* public Morgue retrieveMorgue(@PathVariable("morgue-id") Long idMorgue) {
    return serviceMorgue.retrieveMorgue(idMorgue);*/

        public MorgueDto retrieveMorgue(@PathVariable("morgue-id") Long idMorgue) {
            return MorgueDto.toDto(serviceMorgue.retrieveMorgue(idMorgue));
        }
    @PostMapping("/add-morgue")
    /*public Morgue addMorgue(@RequestBody Morgue morgue ) {
       return serviceMorgue.addMorgue(morgue);*/
        public MorgueDto addMorgue(@RequestBody MorgueDto morgue ) {
            Morgue entity=MorgueDto.toEntity(morgue);
            return MorgueDto.toDto(serviceMorgue.addMorgue(entity));

    }

    @DeleteMapping("/remove-morgue/{morgue-id}")
    public void removeMorgue(@PathVariable("morgue-id") Long idMorgue) {
        serviceMorgue.removeMorgue(idMorgue);
    }
    @PutMapping("/update-morgue")
    /*public Morgue updateMorgue(@RequestBody Morgue morgue) {
            return serviceMorgue.updateMorgue(morgue);*/
        public MorgueDto updateMorgue(@RequestBody MorgueDto morgue) {
            Morgue entity=MorgueDto.toEntity(morgue);
            return MorgueDto.toDto(serviceMorgue.updateMorgue(entity));
    }
    @PutMapping("/morgue/{idetab}")
    @ResponseBody
    /*public Morgue addMorgueToEtab(@RequestBody Morgue morgue, @PathVariable("idetab")Long idetab ) {
        return serviceMorgue.addMorgueAndAssignToEtabliss(morgue,idetab);*/

    public Morgue addMorgueToEtab(@RequestBody MorgueDto morgue, @PathVariable("idetab")Long idetab ) {
        Morgue entity=MorgueDto.toEntity(morgue);
        return serviceMorgue.addMorgueAndAssignToEtabliss(entity,idetab);

    }
}


