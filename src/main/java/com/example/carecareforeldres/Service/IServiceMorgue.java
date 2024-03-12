package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Morgue;

import java.util.List;

public interface IServiceMorgue {
    public Morgue addMorgue(Morgue morgue);
    public List<Morgue> retrieveAllMorgues();

    public Morgue updateMorgue(Morgue morgue);

    public Morgue retrieveMorgue(Long idMorgue);

    void removeMorgue(Long idMorgue);

}
