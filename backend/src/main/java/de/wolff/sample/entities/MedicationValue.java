package de.wolff.sample.entities;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class MedicationValue {

    @Temporal(TemporalType.DATE)
    private Date since;

    @Temporal(TemporalType.DATE)
    private Date until;

    private String name;

    private String activeIngredient;

    private double dose;

    private String unit;

    private String pzn;

    private String atc;

    public MedicationValue(Date since, Date until, String name, String activeIngredient, double dose, String unit, String pzn, String atc) {
        this.since = since;
        this.until = until;
        this.name = name;
        this.activeIngredient = activeIngredient;
        this.dose = dose;
        this.unit = unit;
        this.pzn = pzn;
        this.atc = atc;
    }

    public MedicationValue() {

    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPzn() {
        return pzn;
    }

    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }
}
