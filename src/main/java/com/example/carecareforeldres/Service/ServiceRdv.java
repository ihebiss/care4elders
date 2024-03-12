package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Rdv;
import com.example.carecareforeldres.Repository.RdvRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceRdv implements IServiceRdv{
    RdvRepository rdvRepository;
    @Override
    public Rdv addRdv(Rdv rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public List<Rdv> retrieveAllRdvs() {
        return rdvRepository.findAll();
    }

    @Override
    public Rdv updateRdv(Rdv rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public Rdv retrieveRdv(Long idRDV) {
        return rdvRepository.findById(idRDV).get();
    }

    @Override
    public void removeRdv(Long idRDV) {
        rdvRepository.deleteById(idRDV);

    }
}
