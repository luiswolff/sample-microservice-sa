package de.wolff.sample.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient {

    private long id;

    @FormParam("gender")
    private String gender;

    private Date birthday;

    private List<Medication> medications;

    private List<Diagnosis> diagnoses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @FormParam("birthday")
    public void setBirthdayAsDate(String birthday) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.birthday = format.parse(birthday);
    }
}
