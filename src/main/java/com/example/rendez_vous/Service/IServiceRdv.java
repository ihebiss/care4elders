package com.example.rendez_vous.Service;

import com.example.rendez_vous.Entity.Rdv;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface IServiceRdv {
    public Rdv addRdv(Rdv rdv);
    public List<Rdv> retrieveAllRdvs();

    public Rdv updateRdv(Rdv rdv);

    public Rdv retrieveRdv(Long idRDV);

    void removeRdv(Long idRDV);
    Rdv AddRdvAndAssign(Rdv rdv,Integer idMedecin, Integer idPatient, LocalDateTime dateRDV)throws MessagingException;
    void exportRdvToExcel(String fileName,Integer idMed) throws IOException;
    public void verifierEtatMedecin();




}
