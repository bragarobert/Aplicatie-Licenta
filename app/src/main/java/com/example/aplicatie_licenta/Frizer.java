package com.example.aplicatie_licenta;

public class Frizer {
    private String numeFrizer;
    private String varsta;

    public Frizer(String numeFrizer, String varsta) {
        this.numeFrizer = numeFrizer;
        this.varsta = varsta;
    }

    public String getNumeFrizer() {
        return numeFrizer;
    }

    public void setNumeFrizer(String numeFrizer) {
        this.numeFrizer = numeFrizer;
    }

    public String getVarsta() {
        return varsta;
    }

    public void setVarsta(String varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Frizer{" +
                "numeFrizer='" + numeFrizer + '\'' +
                ", varsta='" + varsta + '\'' +
                '}';
    }
}
