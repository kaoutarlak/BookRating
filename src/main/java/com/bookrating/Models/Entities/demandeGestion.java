package com.bookrating.Models.Entities;

public class demandeGestion {
    private int id ;
    private String message;
    private String etatDemande;
    private String loginAuteur;
    private String loginAdmin;
    private int idLivre;

    public demandeGestion() {
    }

    public demandeGestion(int id, String message, String etatDemande, String loginAuteur, String loginAdmin, int idLivre) {
        this.id = id;
        this.message = message;
        this.etatDemande = etatDemande;
        this.loginAuteur = loginAuteur;
        this.loginAdmin = loginAdmin;
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

    public String getLoginAuteur() {
        return loginAuteur;
    }

    public void setLoginAuteur(String loginAuteur) {
        this.loginAuteur = loginAuteur;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
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
                ", codeAuteur='" + loginAuteur + '\'' +
                ", matriculeAdmin='" + loginAdmin + '\'' +
                ", idLivre=" + idLivre +
                '}';
    }
}
