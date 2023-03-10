package com.bookrating.Models.Entities;

public class demandeGestion {
    private int id ;
    private String message;
    private String etatDemande;
    private String codeAuteur;
    private String matriculeAdmin;
    private int idLivre;

    public demandeGestion() {
    }

    public demandeGestion(int id, String message, String etatDemande, String codeAuteur, String matriculeAdmin, int idLivre) {
        this.id = id;
        this.message = message;
        this.etatDemande = etatDemande;
        this.codeAuteur = codeAuteur;
        this.matriculeAdmin = matriculeAdmin;
        this.idLivre = idLivre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }

    public String getCodeAuteur() {
        return codeAuteur;
    }

    public void setCodeAuteur(String codeAuteur) {
        this.codeAuteur = codeAuteur;
    }

    public String getMatriculeAdmin() {
        return matriculeAdmin;
    }

    public void setMatriculeAdmin(String matriculeAdmin) {
        this.matriculeAdmin = matriculeAdmin;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    @Override
    public String toString() {
        return "demandeGestion{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", etatDemande='" + etatDemande + '\'' +
                ", codeAuteur='" + codeAuteur + '\'' +
                ", matriculeAdmin='" + matriculeAdmin + '\'' +
                ", idLivre=" + idLivre +
                '}';
    }
}
