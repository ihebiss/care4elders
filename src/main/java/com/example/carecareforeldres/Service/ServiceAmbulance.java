package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Repository.AmbulanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceAmbulance implements IServiceAmbulance{
    AmbulanceRepository ambulanceRepository;
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
}
