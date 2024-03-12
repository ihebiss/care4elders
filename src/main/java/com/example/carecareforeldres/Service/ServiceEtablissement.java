package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Repository.EtablissementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceEtablissement implements IServiceEtablissement {
    EtablissementRepository etablissementRepository;
    @Override
    public Etablissement addEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    @Override
    public List<Etablissement> retrieveAllEtablissements() {
        return etablissementRepository.findAll();
    }

    @Override
    public Etablissement updateEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    @Override
    public Etablissement retrieveEtablissement(Long idEtab) {
        return etablissementRepository.findById(idEtab).get();
    }

    @Override
    public void removeEtablissement(Long idEtab) {
        etablissementRepository.deleteById(idEtab);

    }
}
