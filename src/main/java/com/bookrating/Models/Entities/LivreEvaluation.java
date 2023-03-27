package com.bookrating.Models.Entities;

import java.time.LocalDate;

public class LivreEvaluation {
    private int id;
    private String titre;
    private String nomAuteur;
    private String image;
    private int nombreEvaluation;
    private double noteMoyenne;

    public LivreEvaluation() {
    }

    public LivreEvaluation(int id, String titre, String nomAuteur, String image, int nombreEvaluation, double noteMoyenne) {
        this.id = id;
        this.titre = titre;
        this.nomAuteur = nomAuteur;
        this.image = image;
        this.nombreEvaluation = nombreEvaluation;
        this.noteMoyenne = noteMoyenne;
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

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNombreEvaluation() {
        return nombreEvaluation;
    }

    public void setNombreEvaluation(int nombreEvaluation) {
        this.nombreEvaluation = nombreEvaluation;
    }

    public double getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }

    @Override
    public String toString() {
        return "LivreEvaluation{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nomAuteur='" + nomAuteur + '\'' +
                ", image='" + image + '\'' +
                ", nombreEvaluation=" + nombreEvaluation +
                ", noteMoyenne=" + noteMoyenne +
                '}';
    }
}
