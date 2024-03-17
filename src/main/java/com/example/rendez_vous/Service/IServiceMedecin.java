package com.example.rendez_vous.Service;

import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Entity.Specialite;

import java.util.List;

public interface IServiceMedecin {
    public Medecin addMedecin(Medecin medecin);
    public List<Medecin> retrieveAllMedecins();

    public Medecin updateMedecin(Medecin medecin);

    public Medecin retrieveMedecin(Integer idMed);
    void removeMedecin(Integer idMed);
    public Medecin retrieveMedecinBySpecialite(Specialite specialite);

}

