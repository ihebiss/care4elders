package com.example.rendez_vous.Service;

import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Entity.Specialite;
import com.example.rendez_vous.Repository.MedecinRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class ServiceMedecin implements IServiceMedecin{
    MedecinRepository medecinRepository;
    @Override
    public Medecin addMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public List<Medecin> retrieveAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin retrieveMedecin(Integer idMed) {
        return medecinRepository.findById(idMed).get();
    }

    @Override
    public void removeMedecin(Integer idMed) {
    medecinRepository.deleteById(idMed);
    }

    @Override
    public Medecin retrieveMedecinBySpecialite(Specialite specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }
}
