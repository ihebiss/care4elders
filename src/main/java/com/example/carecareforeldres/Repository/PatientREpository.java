package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientREpository extends JpaRepository<Patient,Integer> {
}
