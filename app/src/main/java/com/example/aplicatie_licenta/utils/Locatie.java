package com.example.aplicatie_licenta.utils;

public class Locatie {
    private String denumire;
    private String contact;
    private String adresa;
    private String imagine;

    public Locatie(String denumire, String contact, String adresa, String imagine) {
        this.denumire = denumire;
        this.contact = contact;
        this.adresa = adresa;
        this.imagine = imagine;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "denumire='" + denumire + '\'' +
                ", contact='" + contact + '\'' +
                ", adresa='" + adresa + '\'' +
                ", imagine='" + imagine + '\'' +
                '}';
    }
}
