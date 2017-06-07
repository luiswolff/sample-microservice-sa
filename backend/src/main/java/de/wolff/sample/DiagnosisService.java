package de.wolff.sample;

import de.wolff.sample.entities.DiagnosisValue;
import de.wolff.sample.entities.PatientEntity;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiagnosisService {

    private final EntityManager em;

    private final long patientId;

    DiagnosisService(EntityManager em, long patientId){
        this.em = em;
        this.patientId = patientId;
    }

    @GET
    public PatientEntity getDiagnoses(){
        return em.find(PatientEntity.class, patientId);
    }

    @POST
    public void addDiagnosis(DiagnosisValue diagnosis) {
        JPAUtils.transactional(em.getTransaction(), () -> {
            PatientEntity patient = em.find(PatientEntity.class, patientId);
            patient.getDiagnoses().add(diagnosis);
            em.merge(patient);
        });

    }

    @PUT
    public void updateDiagnosis(List<DiagnosisValue> diagnoses){
        JPAUtils.transactional(em.getTransaction(), () -> {
            PatientEntity patient = em.find(PatientEntity.class, patientId);
            patient.setDiagnoses(diagnoses);
            em.merge(patient);
        });
    }
}
