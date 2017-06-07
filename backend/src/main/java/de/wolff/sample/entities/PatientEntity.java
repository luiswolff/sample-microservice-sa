package de.wolff.sample.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Entity(name = "patients")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PatientEntity {

    @Id
    @GeneratedValue()
    private long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="diagnoses",
            joinColumns=@JoinColumn(name="patient_id")
    )
    @OrderBy("date, diagnosis")
    private List<DiagnosisValue> diagnoses;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="medications",
            joinColumns=@JoinColumn(name="patient_id")
    )
    @OrderBy("since, name")
    private List<MedicationValue> medications;

    public PatientEntity() {
    }

    public PatientEntity(Gender gender, Date birthday, List<DiagnosisValue> diagnoses, List<MedicationValue> medications) {
        this.gender = gender;
        this.birthday = birthday;
        this.diagnoses = diagnoses;
        this.medications = medications;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<DiagnosisValue> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<DiagnosisValue> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<MedicationValue> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationValue> medications) {
        this.medications = medications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientEntity that = (PatientEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
