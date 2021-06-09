package com.example.aplicatie_licenta;

public class Utilizator {
    private String numeUtilizator;
    private String email;
    private String parola;

    public Utilizator() {
    }

    public Utilizator(String numeUtilizator, String email, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.email = email;
        this.parola = parola;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


}
