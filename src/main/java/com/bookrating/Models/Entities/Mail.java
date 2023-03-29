package com.bookrating.Models.Entities;

import java.util.ArrayList;
import java.util.List;

public class Mail {
    private List<String> membreList =new ArrayList<>();
    private String sujet;
    private String message;
    private String membre;

    public Mail() {
    }

    public Mail(List<String> membres, String sujet, String message, String membre) {
        this.membreList = membres;
        this.sujet = sujet;
        this.message = message;
        this.membre = membre;
    }

    public List<String> getMembres() {
        return membreList;
    }

    public void setMembres(List<String> membres) {
        this.membreList = membres;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMembre() {
        return membre;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "membres=" + membreList +
                ", sujet='" + sujet + '\'' +
                ", message='" + message + '\'' +
                ", membre='" + membre + '\'' +
                '}';
    }
}
