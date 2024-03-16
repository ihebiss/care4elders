package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.EtatAmb;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Repository.AmbulanceRepository;
import com.example.carecareforeldres.Repository.EtablissementRepository;
import com.example.carecareforeldres.Repository.MorgueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceEtablissement implements IServiceEtablissement {
    EtablissementRepository etablissementRepository;
    MorgueRepository morgueRepository;
    AmbulanceRepository ambulanceRepository;
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

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void Changement_Etat_Ambul() {
       LocalDate date=LocalDate.now();
        List<Etablissement>etablissements=etablissementRepository.findAll();
        for (Etablissement e:etablissements){
            for (Ambulance ambulance:e.getAmbulances()){
                if (date.equals(ambulance.getDateDernEntret().plusYears(1))){
                    ambulance.setEtatAmb(EtatAmb.MAINTENANCE);
                    ambulance.setBusy(true);
                    ambulance.setDateDernEntret(date);
                    ambulanceRepository.save(ambulance);

                }
            }
        }

    }
    @Scheduled(cron = "0 0 0 *  * *")
    @Override
    public void MaintenanceToFonctionelle() {
        LocalDate date=LocalDate.now();
        List<Etablissement> etablissements =etablissementRepository.findAll();
        for (Etablissement e:etablissements){
            for (Ambulance ambulance:e.getAmbulances()){
                if (ambulance.getEtatAmb()==EtatAmb.MAINTENANCE){
                    if (date.equals(ambulance.getDateDernEntret().plusDays(1))){
                        ambulance.setEtatAmb(EtatAmb.FONCTIONNELLE);
                        ambulance.setBusy(false);
                        ambulanceRepository.save(ambulance);
                    }
                }
            }
        }

    }

}
