package de.wolff.sample.model;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Diagnosis {

    private String diagnosis;

    private Date date;

    private String icd10;

    private String icdVersion;

    public Diagnosis() {
    }

    private Diagnosis(String diagnosis, String date, String icd10, String icdVersion) {
        this.diagnosis = diagnosis;
        this.date = Util.dateFromWWWForm(date);
        this.icd10 = icd10;
        this.icdVersion = icdVersion;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @FormParam("diagnosis")
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIcd10() {
        return icd10;
    }

    @FormParam("icd10")
    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public String getIcdVersion() {
        return icdVersion;
    }

    @FormParam("icdVersion")
    public void setIcdVersion(String icdVersion) {
        this.icdVersion = icdVersion;
    }

    @FormParam("date")
    public void setDateFromString(String date) {
        this.date = Util.dateFromWWWForm(date);
    }

    public static List<Diagnosis> from(MultivaluedMap<String, String> formula) {
        int count = Integer.parseInt(formula.getFirst("count"));
        List<String> names = formula.get("diagnosis");
        List<String> dates = formula.get("date");
        List<String> icds = formula.get("icd");
        List<String> icdVersions = formula.get("icdVersion");

        List<Diagnosis> diagnoses = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            diagnoses.add(new Diagnosis(names.get(i),
                    dates.get(i),
                    icds.get(i),
                    icdVersions.get(i)));
        }
        return diagnoses;
    }
}
