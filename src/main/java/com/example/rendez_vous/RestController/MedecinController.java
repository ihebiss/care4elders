package com.example.rendez_vous.RestController;

import com.example.rendez_vous.Dto.MedecinDto;
import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Service.IServiceMedecin;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/medecin")
public class MedecinController {
    IServiceMedecin serviceMedecin;
    @GetMapping("/retrieve-all-medecin")

    public List<MedecinDto> getMedecins() {
        List<Medecin> medecins = serviceMedecin.retrieveAllMedecins();
        List<MedecinDto>list=medecins.stream().map(medecin ->MedecinDto.toDto(medecin)).toList();
        return list ;

    }

    @GetMapping("/retrieve-medecin/{medecin-id}")

    public MedecinDto retrieveMed(@PathVariable("medecin-id") Integer idMed) {
        return MedecinDto.toDto(serviceMedecin.retrieveMedecin(idMed));
    }
    @PostMapping("/add-medecin")

    public MedecinDto addMedecin(@RequestBody MedecinDto medecin) {
        Medecin entity=MedecinDto.toEntity(medecin);
        return MedecinDto.toDto(serviceMedecin.addMedecin(entity));


    }

    @DeleteMapping("/remove-medecin/{medecin-id}")
    public void removeMed(@PathVariable("medecin-id") Integer idMed) {
        serviceMedecin.removeMedecin(idMed);
    }
    @PutMapping("/update-med")

    public MedecinDto updateMed(@RequestBody MedecinDto medecin) {
        Medecin entity=MedecinDto.toEntity(medecin);
        return MedecinDto.toDto(serviceMedecin.updateMedecin(entity));
    }

}
