package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Rdv;

import java.util.List;

public interface IServiceRdv {
    public Rdv addRdv(Rdv rdv);
    public List<Rdv> retrieveAllRdvs();

    public Rdv updateRdv(Rdv rdv);

    public Rdv retrieveRdv(Long idRDV);

    void removeRdv(Long idRDV);

}
