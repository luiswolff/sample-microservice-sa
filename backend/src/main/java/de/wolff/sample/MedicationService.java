package de.wolff.sample;

import de.wolff.sample.entities.MedicationValue;
import de.wolff.sample.entities.PatientEntity;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicationService {

    private final EntityManager em;

    private final long patientId;

    MedicationService(EntityManager em, long patientId){
        this.em = em;
        this.patientId = patientId;
    }

    @GET
    public PatientEntity getMedications(){
        return em.find(PatientEntity.class, patientId);
    }

    @POST
    public void addMedication(MedicationValue medication){
        JPAUtils.transactional(em.getTransaction(), () -> {
            PatientEntity patient = em.find(PatientEntity.class, patientId);
            patient.getMedications().add(medication);
            em.merge(patient);
        });
    }

    @PUT
    public void updateMedication(List<MedicationValue> medications){
        JPAUtils.transactional(em.getTransaction(), () -> {
            PatientEntity patient = em.find(PatientEntity.class, patientId);
            patient.setMedications(medications);
            em.merge(patient);
        });
    }
}
