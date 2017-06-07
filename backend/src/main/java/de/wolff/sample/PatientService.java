package de.wolff.sample;

import de.wolff.sample.entities.PatientEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("backend/api/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("samplePU");

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(emf::close));
    }

    @GET
    public List<PatientEntity> loadPatients() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select p from patients p", PatientEntity.class).getResultList();
    }

    @POST
    public void addPatient(PatientEntity patient) {
        EntityManager em = emf.createEntityManager();
        JPAUtils.transactional(em.getTransaction(), () -> {
            em.persist(patient);
            if (patient.getMedications() != null) patient.getMedications().forEach(em::persist);
            if (patient.getDiagnoses() != null) patient.getDiagnoses().forEach(em::persist);
        });
    }

    @GET
    @Path("{patientId}")
    public PatientEntity loadPatient(@PathParam("patientId") long patientId) {
        EntityManager em = emf.createEntityManager();
        return em.find(PatientEntity.class, patientId);
    }

    @PUT
    @Path("{patientId}")
    public void updatePatient(@PathParam("patientId") long patientId, PatientEntity patient) {
        EntityManager em = emf.createEntityManager();
        JPAUtils.transactional(em.getTransaction(), () -> {
            patient.setId(patientId);
            em.merge(patient);
        });

    }

    @DELETE
    @Path("{patientId}")
    public void deletePatient(@PathParam("patientId") long patientId) {
        EntityManager em = emf.createEntityManager();
        JPAUtils.transactional(em.getTransaction(), () -> {
            PatientEntity patient = em.getReference(PatientEntity.class, patientId);
            if (patient != null) {
                em.remove(patient);
            }
        });
    }

    @Path("{patientId}/medications")
    public MedicationService getMedicationService(@PathParam("patientId") long patientId) {
        EntityManager em = emf.createEntityManager();
        return new MedicationService(em, patientId);
    }

    @Path("{patientId}/diagnoses")
    public DiagnosisService getDiagnosisService(@PathParam("patientId") long patientId) {
        EntityManager em = emf.createEntityManager();
        return new DiagnosisService(em, patientId);
    }
}
