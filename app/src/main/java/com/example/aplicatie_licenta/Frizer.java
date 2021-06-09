package com.example.aplicatie_licenta;

public class Frizer {
    private String numeFrizer;

    public Frizer(String numeFrizer) {
        this.numeFrizer = numeFrizer;
    }

    public String getNumeFrizer() {
        return numeFrizer;
    }

    public void setNumeFrizer(String numeFrizer) {
        this.numeFrizer = numeFrizer;
    }

    @Override
    public String toString() {
        return "Frizer{" +
                "numeFrizer='" + numeFrizer + '\'' +
                '}';
    }
}
