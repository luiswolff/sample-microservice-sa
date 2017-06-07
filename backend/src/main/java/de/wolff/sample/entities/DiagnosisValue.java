package de.wolff.sample.entities;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class DiagnosisValue {

    private String diagnosis;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String icd10;

    private String icdVersion;

    public DiagnosisValue() {
    }

    public DiagnosisValue(String diagnosis, Date date, String icd10, String icdVersion) {
        this.diagnosis = diagnosis;
        this.date = date;
        this.icd10 = icd10;
        this.icdVersion = icdVersion;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

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

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public String getIcdVersion() {
        return icdVersion;
    }

    public void setIcdVersion(String idcVersion) {
        this.icdVersion = idcVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagnosisValue that = (DiagnosisValue) o;

        return diagnosis.equals(that.diagnosis)
                && (icd10 != null ? icd10.equals(that.icd10) : that.icd10 == null)
                && (icdVersion != null ? icdVersion.equals(that.icdVersion) : that.icdVersion == null);
    }

    @Override
    public int hashCode() {
        int result = diagnosis.hashCode();
        result = 31 * result + (icd10 != null ? icd10.hashCode() : 0);
        result = 31 * result + (icdVersion != null ? icdVersion.hashCode() : 0);
        return result;
    }
}
