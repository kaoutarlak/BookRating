package com.bookrating.Models.Entities;

public class livreLu {
    private int id;
    private String login;
    private int idLivre;

    public livreLu() {
    }

    public livreLu(int id, String login, int idLivre) {
        this.id = id;
        this.login = login;
        this.idLivre = idLivre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    @Override
    public String toString() {
        return "livreLu{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", idLivre=" + idLivre +
                '}';
    }
}
