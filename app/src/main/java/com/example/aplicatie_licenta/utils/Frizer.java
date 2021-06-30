package com.example.aplicatie_licenta.utils;

public class Frizer {
    private String numeFrizer;
    private String varsta;
    private String descriere;
    private String imagine;

    public Frizer(String numeFrizer, String varsta, String descriere, String imagine) {
        this.numeFrizer = numeFrizer;
        this.varsta = varsta;
        this.descriere = descriere;
        this.imagine = imagine;
    }

    @Override
    public String toString() {
        return "Frizer{" +
                "numeFrizer='" + numeFrizer + '\'' +
                ", varsta='" + varsta + '\'' +
                ", descriere='" + descriere + '\'' +
                ", imagine='" + imagine + '\'' +
                '}';
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }
}
