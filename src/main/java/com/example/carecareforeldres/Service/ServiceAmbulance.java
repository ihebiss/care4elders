package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Repository.AmbulanceRepository;
import com.example.carecareforeldres.Repository.EtablissementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceAmbulance implements IServiceAmbulance{
    AmbulanceRepository ambulanceRepository;
    EtablissementRepository etablissementRepository;
    @Override
    public Ambulance addAmbulance(Ambulance ambulance) {
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public List<Ambulance> retrieveAllAmbulances() {
        return ambulanceRepository.findAll();
    }

    @Override
    public Ambulance updateAmbulance(Ambulance ambulance) {
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public Ambulance retrieveAmbulance(Long idAmb) {
        return ambulanceRepository.findById(idAmb).get();
    }

    @Override
    public void removeAmbulance(Long idAmb) {
    ambulanceRepository.deleteById(idAmb);
    }

    @Override
    public Ambulance addAmbulanceAndAssignToEtabliss(Ambulance ambulance, Long idEtab) {
        Etablissement etablissement=etablissementRepository.findById(idEtab).get();
        ambulance.setEtablissement(etablissement);
        ambulanceRepository.save(ambulance);
        return ambulance;
    }

    @Override
    public Ambulance UnassignAmbulanceEtabliss(Long idAmb) {
        Ambulance ambulance=ambulanceRepository.findById(idAmb).get();
        ambulance.setEtablissement(null);
        ambulanceRepository.save(ambulance);
        return ambulance;
    }
}
