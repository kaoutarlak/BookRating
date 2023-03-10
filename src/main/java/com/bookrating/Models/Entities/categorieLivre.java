package com.bookrating.Models.Entities;

public class categorieLivre {
    private int id ;
    private String titre;

    public categorieLivre() {
    }

    public categorieLivre(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "categorieLivre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }
}
