package com.bookrating.Models.Entities;

public class auteur {
    private String code;
    private String login;

    public auteur() {
    }

    public auteur(String code, String login) {
        this.code = code;
        this.login = login;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "auteur{" +
                "code='" + code + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
