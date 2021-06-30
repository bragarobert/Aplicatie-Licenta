package com.example.aplicatie_licenta.utils;

public class Serviciu {
    private String denumire;
    private String descriere;
    private String imagine;
    private int pret;

    public Serviciu(String denumire, String descriere, String imagine, int pret) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.imagine = imagine;
        this.pret = pret;
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

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Serviciu{" +
                "denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                ", imagine='" + imagine + '\'' +
                ", pret=" + pret +
                '}';
    }
}
