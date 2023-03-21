package com.bookrating.Models.Entities;

public class signalement {

    private int id;

    private String message;
    private int verifier;
    private String login;
    private int idAvis;

    public signalement() {
    }

    public signalement(int id, String message, int verifier, String login, int idAvis) {
        this.id = id;
        this.message = message;
        this.verifier = verifier;
        this.login = login;
        this.idAvis = idAvis;
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

    public int getVerifier() {
        return verifier;
    }

    public void setVerifier(int verifier) {
        this.verifier = verifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    @Override
    public String toString() {
        return "signalement{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", verifier=" + verifier +
                ", login='" + login + '\'' +
                ", idAvis=" + idAvis +
                '}';
    }
}
