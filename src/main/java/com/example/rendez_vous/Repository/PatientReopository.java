package com.example.rendez_vous.Repository;

import com.example.rendez_vous.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientReopository extends JpaRepository<Patient,Integer> {
}
