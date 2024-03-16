package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Repository.EtablissementRepository;
import com.example.carecareforeldres.Repository.MorgueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceMorgue implements IServiceMorgue{
    MorgueRepository morgueRepository;
    EtablissementRepository etablissementRepository;
    @Override
    public Morgue addMorgue(Morgue morgue) {
        return morgueRepository.save(morgue);
    }

    @Override
    public List<Morgue> retrieveAllMorgues() {
        return morgueRepository.findAll();
    }

    @Override
    public Morgue updateMorgue(Morgue morgue) {
        return morgueRepository.save(morgue);
    }

    @Override
    public Morgue retrieveMorgue(Long idMorgue) {
        return morgueRepository.findById(idMorgue).get();
    }

    @Override
    public void removeMorgue(Long idMorgue) {
    morgueRepository.deleteById(idMorgue);
    }


    @Override
    public Morgue addMorgueAndAssignToEtabliss(Morgue morgue, Long idEtab) {
        Etablissement etablissement=etablissementRepository.findById(idEtab).get();
        etablissement.setMorgue(morgue);
        etablissementRepository.save(etablissement);
        return morgue;

    }



}
