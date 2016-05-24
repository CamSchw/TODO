package com.example.ensai.todo;

/**
 * Created by ensai on 27/04/16.
 */
public class Element {

    private String nom = null;
    private String commentaire = null;
    private String type = null;

    public Element() {

    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNom() {
        return nom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return nom + '\n' + commentaire;
    }

}
