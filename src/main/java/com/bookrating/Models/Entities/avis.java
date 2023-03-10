package com.bookrating.Models.Entities;

import java.time.LocalDate;

public class avis {
    private int id ;
    private LocalDate datePost;
    private String commentaire;
    private int nbLikes;
    private String login;
    private int idLivre;

    public avis() {
    }

    public avis(int id, LocalDate datePost, String commentaire, int nbLikes, String login, int idLivre) {
        this.id = id;
        this.datePost = datePost;
        this.commentaire = commentaire;
        this.nbLikes = nbLikes;
        this.login = login;
        this.idLivre = idLivre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatePost() {
        return datePost;
    }

    public void setDatePost(LocalDate datePost) {
        this.datePost = datePost;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
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
        return "avis{" +
                "id=" + id +
                ", datePost=" + datePost +
                ", commentaire='" + commentaire + '\'' +
                ", nbLikes=" + nbLikes +
                ", login='" + login + '\'' +
                ", idLivre=" + idLivre +
                '}';
    }
}
