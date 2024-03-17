package com.example.rendez_vous.RestController;

import com.example.rendez_vous.Dto.PatientDto;
import com.example.rendez_vous.Entity.Patient;
import com.example.rendez_vous.Service.IServicePatient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    IServicePatient servicePatient;
    @GetMapping("/retrieve-all-patient")

    public List<PatientDto> getPatients() {
        List<Patient> patients = servicePatient.retrieveAllPatients();
        List<PatientDto>list=patients.stream().map(patient ->PatientDto.toDto(patient)).toList();
        return list ;

    }

    @GetMapping("/retrieve-patient/{patient-id}")

    public PatientDto retrievePatient(@PathVariable("patient-id") Integer idpatient) {
        return PatientDto.toDto(servicePatient.retrievePatient(idpatient));
    }
    @PostMapping("/add-patient")

    public PatientDto addPatient(@RequestBody PatientDto patient) {
        Patient entity=PatientDto.toEntity(patient);
        return PatientDto.toDto(servicePatient.addPatient(entity));

    }

    @DeleteMapping("/remove-patient/{patient-id}")
    public void removePatient(@PathVariable("patient-id") Integer idpatient) {
        servicePatient.removePatient(idpatient);
    }
    @PutMapping("/update-patient")

    public PatientDto updatePatient(@RequestBody PatientDto patient) {
        Patient entity=PatientDto.toEntity(patient);
        return PatientDto.toDto(servicePatient.updatePatient(entity));
    }

}
