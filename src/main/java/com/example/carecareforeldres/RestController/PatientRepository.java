package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Service.IaddPatient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientRepository {
    IaddPatient iaddPatient;
    @PostMapping("/add")
   public Patient ajouterPation( @RequestBody Patient patient){
        Patient p1=iaddPatient.ajouter(patient);
        return p1;
    }
}
