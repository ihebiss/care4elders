package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ordannance;
import com.example.carecareforeldres.Repository.OrdonnanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceOrdannance implements IServiceOrdonnance{
    OrdonnanceRepository ordonnanceRepository;
    @Override
    public Ordannance addOrdannance(Ordannance ordannance) {
        return ordonnanceRepository.save(ordannance);
    }

    @Override
    public List<Ordannance> retrieveAllOrdannances() {
        return ordonnanceRepository.findAll();
    }

    @Override
    public Ordannance updateOrdannance(Ordannance ordannance) {
        return ordonnanceRepository.save(ordannance);
    }

    @Override
    public Ordannance retrieveOrdannance(Long idOrd) {
        return ordonnanceRepository.findById(idOrd).get();
    }

    @Override
    public void removeOrdannance(Long idOrd) {
        ordonnanceRepository.deleteById(idOrd);

    }
}
