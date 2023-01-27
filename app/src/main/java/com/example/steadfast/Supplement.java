package com.example.steadfast;

public class Supplement {
    private String supplement_name;
    private String supplement_dosage;
    private boolean taken;

    public Supplement(String supplement_name, String supplement_dosage) {
        this.supplement_name = supplement_name;
        this.supplement_dosage = supplement_dosage;
        this.taken = false;
    }

    public String getSupplementName() {
        return supplement_name;
    }

    public void setSupplementName(String supplement_name) {
        this.supplement_name = supplement_name;
    }

    public String getSupplementDosage() {
        return supplement_dosage;
    }

    public void setSupplementDosage(String supplement_dosage) {
        this.supplement_dosage = supplement_dosage;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}

