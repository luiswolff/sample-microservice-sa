package de.wolff.sample;

import de.wolff.sample.model.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.StatusType;
import java.util.List;
import java.util.function.Supplier;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Path("")
public class PatientController {

    private Client client;

    private WebTarget backend;

    @PostConstruct
    public void init(){

        final String schema = System.getProperty("backend.SCHEMA", "http");
        final String host = System.getProperty("backend.HOST", "localhost");
        final Integer port = Integer.getInteger("backend.PORT", 8080);
        final String path = System.getProperty("backend.PATH", "backend/api");

        final String endpoint = System.getProperty("backend.ENDPOINT", schema + "://" + host + ":" + port + "/" + path);

        client = ClientBuilder.newClient();
        backend = client.target(endpoint);
    }

    @GET
    @Path("patients.html")
    public Viewable allPatients(){
        List<Patient> patients = backend.path("patients")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Patient>>(){});
        return new Viewable("/WEB-INF/pages/all_patients.jsp", patients);
    }

    @POST
    @Path("{patientId}")
    public Response deletePatient(@PathParam("patientId") long patientId, @FormParam("delete") String action){
        if (action == null) throw new BadRequestException();
        StatusType info = backend.path("patients/" + patientId)
                .request()
                .delete()
                .getStatusInfo();
        return ifOkSeeOther(info, "allPatients");
    }

    @GET
    @Path("createPatient.html")
    public Viewable patientFormula(){
        return new Viewable("/WEB-INF/pages/single_patient.jsp");
    }

    @POST
    @Path("createPatient.html")
    public Response createPatient(@BeanParam Patient patient){
        StatusType status = backend.path("patients")
                .request()
                .post(Entity.entity(patient, MediaType.APPLICATION_JSON))
                .getStatusInfo();
        return ifOkSeeOther(status, "allPatients");
    }

    @GET
    @Path("{patientId}/edit.html")
    public Viewable editPatient(@PathParam("patientId") long patientId){
        Patient patient = backend.path("patients/" + patientId).request(MediaType.APPLICATION_JSON).get(Patient.class);
        if (patient != null){
            return new Viewable("/WEB-INF/pages/single_patient.jsp", patient);
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Path("{patientId}/edit.html")
    public Response updatePatient(@PathParam("patientId") long patientId, @BeanParam Patient patient){
        StatusType status = backend.path("patients/" + patientId)
                .request()
                .put(Entity.entity(patient, MediaType.APPLICATION_JSON))
                .getStatusInfo();
        return ifOkSeeOther(status, "allPatients");
    }

    @GET
    @Path("{patientId}/diagnoses.html")
    public Viewable diagnosesForPatient(@PathParam("patientId") long patientId){
        Patient patient = backend.path("patients/" + patientId + "/diagnoses")
                .request(MediaType.APPLICATION_JSON)
                .get(Patient.class);
        return new Viewable("/WEB-INF/pages/diagnoses.jsp", patient);
    }

    @POST
    @Path("{patientId}/diagnoses.html")
    public Response updateDiagnoses(@PathParam("patientId") long patientId, MultivaluedMap<String, String> formula){
        StatusType status = backend.path("patients/" + patientId + "/diagnoses")
                .request()
                .put(Entity.json(new GenericEntity<List<Diagnosis>>(Diagnosis.from(formula)){}))
                .getStatusInfo();
        return ifOkAnswer(status, () -> Response.ok(diagnosesForPatient(patientId)).build());
    }

    @POST
    @Path("{patientId}/diagnoses/add")
    public Response addDiagnoses(@PathParam("patientId") long patientId, @BeanParam Diagnosis diagnosis){
        StatusType status = backend.path("patients/" + patientId + "/diagnoses")
                .request()
                .post(Entity.json(diagnosis))
                .getStatusInfo();
        return ifOkSeeOther(status, "diagnosesForPatient", patientId);
    }

    @GET
    @Path("{patientId}/medications.html")
    public Viewable medicationsForPatient(@PathParam("patientId") long patientId){
        Patient patient = backend.path("patients/" + patientId + "/medications")
                .request(MediaType.APPLICATION_JSON)
                .get(Patient.class);
        return new Viewable("/WEB-INF/pages/medications.jsp", patient);
    }

    @POST
    @Path("{patientId}/medications.html")
    public Response updateMedications(@PathParam("patientId") long patientId, MultivaluedMap<String, String> formula){
        StatusType status = backend.path("patients/" + patientId + "/medications")
                .request()
                .put(Entity.json(new GenericEntity<List<Medication>>(Medication.from(formula)){}))
                .getStatusInfo();
        return ifOkAnswer(status, () -> Response.ok(medicationsForPatient(patientId)).build());
    }

    @POST
    @Path("{patientId}/medications/add")
    public Response addMedication(@PathParam("patientId") long patientId, @BeanParam Medication medication){
        StatusType status = backend.path("patients/" + patientId + "/medications")
                .request()
                .post(Entity.json(medication))
                .getStatusInfo();
        return ifOkSeeOther(status, "medicationsForPatient", patientId);
    }

    @PreDestroy
    public void destroy(){
        client.close();
    }

    private Response ifOkSeeOther(StatusType status, String method, Object ... templateVariables) {
        return ifOkAnswer(status, () -> Response
                .seeOther(UriBuilder
                        .fromPath("/frontend")
                        .path(PatientController.class)
                        .path(PatientController.class, method)
                        .build(templateVariables))
                .build());
    }

    private Response ifOkAnswer(StatusType info, Supplier<Response> responseSupplier){
        if (info.getFamily() == Response.Status.Family.SUCCESSFUL){
            return responseSupplier.get();
        } else {
            return Response.serverError().build();
        }
    }
}
