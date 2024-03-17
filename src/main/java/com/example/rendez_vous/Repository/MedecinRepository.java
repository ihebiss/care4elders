package com.example.rendez_vous.Repository;

import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Entity.Rdv;
import com.example.rendez_vous.Entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin,Integer> {
    @Query("SELECT COUNT(r) FROM Medecin m JOIN m.rdvs r WHERE m.idMed = :idmed AND r.dateRDV = :date")
    Long compter(@Param("idmed") Integer idMed, @Param("date") LocalDateTime dateRDV);
    Medecin findBySpecialite(Specialite specialite);


}
