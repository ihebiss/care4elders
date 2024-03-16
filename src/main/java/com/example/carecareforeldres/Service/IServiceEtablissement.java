package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;

import java.util.List;

public interface IServiceEtablissement {
    public Etablissement addEtablissement(Etablissement etablissement);
    public List<Etablissement> retrieveAllEtablissements();

    public Etablissement updateEtablissement(Etablissement etablissement);

    public Etablissement retrieveEtablissement(Long idEtab);

    void removeEtablissement(Long idEtab);
    public void Changement_Etat_Ambul();
    public void MaintenanceToFonctionelle();

}
