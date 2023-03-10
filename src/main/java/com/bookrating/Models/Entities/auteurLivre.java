package com.bookrating.Models.Entities;

public class auteurLivre {
    private int id;
    private String codeAuteur;
    private int idLivre;

    public auteurLivre() {
    }

    public auteurLivre(int id, String codeAuteur, int idLivre) {
        this.id = id;
        this.codeAuteur = codeAuteur;
        this.idLivre = idLivre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeAuteur() {
        return codeAuteur;
    }

    public void setCodeAuteur(String codeAuteur) {
        this.codeAuteur = codeAuteur;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    @Override
    public String toString() {
        return "auteurLivre{" +
                "id=" + id +
                ", codeAuteur='" + codeAuteur + '\'' +
                ", idLivre=" + idLivre +
                '}';
    }
}
