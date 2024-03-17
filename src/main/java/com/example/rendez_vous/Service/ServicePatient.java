package com.example.rendez_vous.Service;


import com.example.rendez_vous.Entity.Patient;
import com.example.rendez_vous.Repository.PatientReopository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class ServicePatient implements IServicePatient {
    PatientReopository patientReopository;
    @Override
    public Patient addPatient(Patient patient) {
        return patientReopository.save(patient);
    }

    @Override
    public List<Patient> retrieveAllPatients() {
        return patientReopository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientReopository.save(patient);
    }

    @Override
    public Patient retrievePatient(Integer idMed) {
        return patientReopository.findById(idMed).get();
    }

    @Override
    public void removePatient(Integer idMed) {
        patientReopository.deleteById(idMed);

    }
}