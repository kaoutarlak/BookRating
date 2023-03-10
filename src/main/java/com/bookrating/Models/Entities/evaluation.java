package com.bookrating.Models.Entities;

public class evaluation {
    private int id;
    private int note;
    private int idCategorieEvaluation;
    private int idAvis;

    public evaluation() {
    }

    public evaluation(int id, int note, int idCategorieEvaluation, int idAvis) {
        this.id = id;
        this.note = note;
        this.idCategorieEvaluation = idCategorieEvaluation;
        this.idAvis = idAvis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIdCategorieEvaluation() {
        return idCategorieEvaluation;
    }

    public void setIdCategorieEvaluation(int idCategorieEvaluation) {
        this.idCategorieEvaluation = idCategorieEvaluation;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    @Override
    public String toString() {
        return "evaluation{" +
                "id=" + id +
                ", note=" + note +
                ", idCategorieEvaluation=" + idCategorieEvaluation +
                ", idAvis=" + idAvis +
                '}';
    }
}
