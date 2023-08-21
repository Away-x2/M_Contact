package com.example.m_contacts.bo;

public class Group {
    private int id;
    private String nom;

    public Group(String nom) {
        this.nom = nom;
    }


    public Group(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom.trim();
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom.trim();
    }

    @Override
    public String toString() {
        return "Group--->" +
                "nom=" + nom + "   "+id;
    }
}
