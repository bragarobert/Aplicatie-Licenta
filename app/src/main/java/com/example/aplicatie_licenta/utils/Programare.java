package com.example.aplicatie_licenta.utils;

public class Programare {
    private String tipServiciu;
    private String data;
    private String ora;
    private String frizer;
    private String locatie;
    private boolean anulata;


    public Programare(String tipServiciu, String data, String ora, String frizer, String locatie, boolean anulata) {
        this.tipServiciu = tipServiciu;
        this.data = data;
        this.ora = ora;
        this.frizer = frizer;
        this.locatie = locatie;
        this.anulata = anulata;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "tipServiciu='" + tipServiciu + '\'' +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                ", frizer='" + frizer + '\'' +
                ", locatie='" + locatie + '\'' +
                ", anulata=" + anulata +
                '}';
    }

    public boolean isAnulata() {
        return anulata;
    }

    public void setAnulata(boolean anulata) {
        this.anulata = anulata;
    }

    public String getFrizer() {
        return frizer;
    }

    public void setFrizer(String frizer) {
        this.frizer = frizer;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getTipServiciu() {
        return tipServiciu;
    }

    public void setTipServiciu(String tipServiciu) {
        this.tipServiciu = tipServiciu;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }
}
