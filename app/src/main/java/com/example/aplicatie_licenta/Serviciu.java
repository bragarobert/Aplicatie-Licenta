package com.example.aplicatie_licenta;

public class Serviciu {
    private String denumire;
    private String descriere;
    private String imagine;

    public Serviciu() {
    }

    public Serviciu(String denumire, String descriere, String imagine) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.imagine = imagine;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
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

    @Override
    public String toString() {
        return "Serviciu{" +
                "denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
