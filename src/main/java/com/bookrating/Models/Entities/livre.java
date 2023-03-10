package com.bookrating.Models.Entities;

import java.time.LocalDate;

public class livre {
    private int id;
    private String titre;
    private String description;
    private String nomAuteur;
    private LocalDate dateParution;
    private String image;
    private int idCategorieLivre;

    public livre() {
    }

    public livre(int id, String titre, String description, String nomAuteur, LocalDate dateParution, String image, int idCategorieLivre) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nomAuteur = nomAuteur;
        this.dateParution = dateParution;
        this.image = image;
        this.idCategorieLivre = idCategorieLivre;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public LocalDate getDateParution() {
        return dateParution;
    }

    public void setDateParution(LocalDate dateParution) {
        this.dateParution = dateParution;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdCategorieLivre() {
        return idCategorieLivre;
    }

    public void setIdCategorieLivre(int idCategorieLivre) {
        this.idCategorieLivre = idCategorieLivre;
    }

    @Override
    public String toString() {
        return "livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", nomAuteur='" + nomAuteur + '\'' +
                ", dateParution=" + dateParution +
                ", image='" + image + '\'' +
                ", idCategorieLivre=" + idCategorieLivre +
                '}';
    }
}
