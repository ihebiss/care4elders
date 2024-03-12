package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ordannance;

import java.util.List;

public interface IServiceOrdonnance {
    public Ordannance addOrdannance(Ordannance ordannance);
    public List<Ordannance> retrieveAllOrdannances();

    public Ordannance updateOrdannance(Ordannance ordannance);

    public Ordannance retrieveOrdannance(Long idOrd);

    void removeOrdannance(Long idOrd);


}
