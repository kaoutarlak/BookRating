package com.bookrating.Models.Entities;

public class administrateur {
    private String matricule;
    private String login;

    public administrateur() {
    }

    public administrateur(String matricule, String login) {
        this.matricule = matricule;
        this.login = login;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "administrateur{" +
                "matricule='" + matricule + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
