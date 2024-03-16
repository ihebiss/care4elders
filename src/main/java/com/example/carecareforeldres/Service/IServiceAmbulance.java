package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ambulance;

import java.util.List;

public interface IServiceAmbulance {
    public Ambulance addAmbulance(Ambulance ambulance);
    public List<Ambulance> retrieveAllAmbulances();

    public Ambulance updateAmbulance(Ambulance ambulance);

    public Ambulance retrieveAmbulance(Long idAmb);

    void removeAmbulance(Long idAmb);
    public Ambulance addAmbulanceAndAssignToEtabliss (Ambulance ambulance, Long idEtab);
    public Ambulance UnassignAmbulanceEtabliss(Long idAmb);




}
