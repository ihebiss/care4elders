package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Repository.PatientREpository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class addPatient implements IaddPatient{
    PatientREpository patientREpository;
    @Override
    public Patient ajouter(Patient patient) {
        Patient patient1= patientREpository.save(patient);
        return patient1;
    }
}
