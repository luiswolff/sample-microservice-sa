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
public class Medication {

    private Date since;

    private Date until;

    private String name;

    private String activeIngredient;

    private double dose;

    private String unit;

    private String pzn;

    private String atc;

    public Medication() {
    }

    private Medication(String since, String until, String name, String activeIngredient, String dose, String unit, String pzn, String atc) {
        this.since = Util.dateFromWWWForm(since);
        this.until = Util.dateFromWWWForm(until);
        this.name = name;
        this.activeIngredient = activeIngredient;
        this.dose = Double.parseDouble(dose);
        this.unit = unit;
        this.pzn = pzn;
        this.atc = atc;
    }

    public Date getSince() {
        return since;
    }

    @FormParam("since")
    public void setSince(String since) {
        this.since =Util.dateFromWWWForm(since);
    }

    public Date getUntil() {
        return until;
    }

    @FormParam("until")
    public void setUntil(String until) {
        this.until = Util.dateFromWWWForm(until);
    }

    public String getName() {
        return name;
    }

    @FormParam("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    @FormParam("activeIngredient")
    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public double getDose() {
        return dose;
    }

    @FormParam("dose")
    public void setDose(double dose) {
        this.dose = dose;
    }

    public String getUnit() {
        return unit;
    }

    @FormParam("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPzn() {
        return pzn;
    }

    @FormParam("pzn")
    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    public String getAtc() {
        return atc;
    }

    @FormParam("atc")
    public void setAtc(String atc) {
        this.atc = atc;
    }

    public static List<Medication> from(MultivaluedMap<String, String> formula) {
        int count = Integer.parseInt(formula.getFirst("count"));
        List<String> since = formula.get("since");
        List<String> until = formula.get("until");
        List<String> ingredient = formula.get("activeIngredient");
        List<String> names = formula.get("name");
        List<String> doses = formula.get("dose");
        List<String> units = formula.get("unit");
        List<String> pzn = formula.get("pzn");
        List<String> atc = formula.get("atc");

        List<Medication> medications = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            medications.add(new Medication(since.get(i),
                    until.get(i),
                    names.get(i),
                    ingredient.get(i),
                    doses.get(i),
                    units.get(i),
                    pzn.get(i),
                    atc.get(i)));
        }
        return medications;
    }
}
