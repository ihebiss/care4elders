package com.example.rendez_vous.Service;

import com.example.rendez_vous.Configurations.NbrRdvMax;
import com.example.rendez_vous.Configurations.PauseExep;
import com.example.rendez_vous.Entity.Medecin;
import com.example.rendez_vous.Entity.Patient;
import com.example.rendez_vous.Entity.Rdv;
import com.example.rendez_vous.Entity.TypePatient;
import com.example.rendez_vous.Repository.MedecinRepository;
import com.example.rendez_vous.Repository.PatientReopository;
import com.example.rendez_vous.Repository.RdvRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceRdv implements IServiceRdv {
    RdvRepository rdvRepository;
    MedecinRepository medecinRepository;
    PatientReopository patientReopository;

    @Override
    public Rdv addRdv(Rdv rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public List<Rdv> retrieveAllRdvs() {
        return rdvRepository.findAll();
    }

    @Override
    public Rdv updateRdv(Rdv rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public Rdv retrieveRdv(Long idRDV) {
        return rdvRepository.findById(idRDV).get();
    }

    @Override
    public void removeRdv(Long idRDV) {
        rdvRepository.deleteById(idRDV);

    }
    private JavaMailSender javaMailSender;
    @Override
    public Rdv AddRdvAndAssign(Rdv rdv, Integer idMedecin, Integer idPatient, LocalDateTime dateRDV)throws MessagingException {
        Medecin medecin = medecinRepository.findById(idMedecin).get();
        Patient patient = patientReopository.findById(idPatient).get();

        if (dateRDV.getHour() >= 12 && dateRDV.getHour() < 15) {
            throw new PauseExep("Nous sommes en Pause");
        }

        List<Rdv> listeRDV = rdvRepository.findLastByMedecin(idMedecin);
        if (!listeRDV.isEmpty()) {
            Rdv lastRdv = listeRDV.get(0);
            if (Duration.between(lastRdv.getDateRDV(), dateRDV).toHours() < 1) {
                dateRDV = lastRdv.getDateRDV().plusHours(1);

            }
        }

            if (medecin.isDisponible()==true){
            rdv.setPatient(patient);
            rdv.setMedecin(medecin);
            rdv.setDateRDV(dateRDV);
            rdvRepository.save(rdv);
        }

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(medecin.getMail());
            helper.setTo(patient.getMail());
            helper.setSubject("Confirmation de rendez-vous");
            helper.setText("Cher " + patient.getNom() + ",\n\nVotre rendez-vous avec le Dr " + medecin.getNom() +
                    " a été confirmé pour le " + rdv.getDateRDV() + ".\n\nCordialement,\n" + medecin.getNom());

            javaMailSender.send(message);
        return rdv;
    }






    @Override
    public void exportRdvToExcel(String fileName, Integer idMed) throws IOException {
        List<Rdv> rdvs = rdvRepository.findLastByMedecin(idMed);


        String desktopPath = System.getProperty("user.home") + "/Desktop/ExelPi/";
        String filePath = desktopPath + fileName;

        if (!fileName.endsWith(".xlsx")) {
            filePath += ".xlsx";
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Rendez-Vous");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("RDV ID");
            header.createCell(1).setCellValue("Médecin");
            header.createCell(2).setCellValue("Patient");
            header.createCell(3).setCellValue("Date Rdv");
            header.createCell(4).setCellValue("Archiver");

            int rowNum = 1;
            for (Rdv rdv : rdvs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rdv.getIdRDV());
                row.createCell(1).setCellValue(rdv.getMedecin().getNom());
                row.createCell(2).setCellValue(rdv.getPatient().getNom());
                row.createCell(3).setCellValue(rdv.getDateRDV().toString());
                row.createCell(4).setCellValue(rdv.isArchiver());
            }

             FileOutputStream fileOut = new FileOutputStream(filePath);
                workbook.write(fileOut);
            }
    }
    //@Scheduled(cron = "5 * * * * *")
    @Override
    public void verifierEtatMedecin() {
        LocalDateTime heure=LocalDateTime.now();
            for (Medecin m:medecinRepository.findAll()){
                for (Rdv r :m.getRdvs()){
                    LocalDateTime debut = r.getDateRDV();
                    LocalDateTime fin = debut.plusHours(1);
            if (heure.isBefore(fin) && heure.isAfter(debut)) {
                m.setDisponible(false);
                medecinRepository.save(m);
        }
            else
            {
                m.setDisponible(true);
                 medecinRepository.save(m);
    }
        }
}
}
}



