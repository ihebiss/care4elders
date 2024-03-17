package com.example.rendez_vous.Service;

import com.example.rendez_vous.Entity.Patient;

import java.util.List;

public interface IServicePatient {
    public Patient addPatient(Patient patient);
    public List<Patient> retrieveAllPatients();

    public Patient updatePatient(Patient patient);

    public Patient retrievePatient(Integer idpatient);
    void removePatient(Integer idpatient);
}

