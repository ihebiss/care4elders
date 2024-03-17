package com.example.rendez_vous.Repository;

import com.example.rendez_vous.Entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RdvRepository extends JpaRepository<Rdv,Long> {
  //  @Query("SELECT COUNT (r) FROM Rdv r join r.medecin m WHERE m.idMed = :idmed AND r.dateRDV =:date")
   // int compter(@Param("idmed") Integer idMed,@Param("date") LocalDateTime dateRDV);
    @Query("SELECT (r) FROM Rdv r join r.medecin m WHERE m.idMed = :idMedecin  ORDER BY r.dateRDV DESC")
    List<Rdv> findLastByMedecin(@Param("idMedecin") Integer idMedecin);


}
