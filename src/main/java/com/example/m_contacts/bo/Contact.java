package com.example.m_contacts.bo;

public class Contact {
    private  int id;
    private String Nom;
    private String Prenom;
    private String TelephonePer;
    private String TelephonPro;
    private String adresse;
    private String EmailPer;
    private String Emailpro;
    private String Genre;

    private int idgroup;

    public Contact(String nom, String prenom, String telephone1, String telephone2, String adresse, String emailPer, String emailpro, String genre) {
        Nom = nom;
        Prenom = prenom;
        TelephonePer = telephone1;
        TelephonPro  = telephone2;
        this.adresse = adresse;
        EmailPer = emailPer;
        Emailpro = emailpro;
        Genre = genre;
    }

    public Contact(int id, String nom, String prenom, String telephonePer, String telephonPro, String adresse, String emailPer, String emailpro, String genre, int idgroup) {
        this.id = id;
        Nom = nom;
        Prenom = prenom;
        TelephonePer = telephonePer;
        TelephonPro = telephonPro;
        this.adresse = adresse;
        EmailPer = emailPer;
        Emailpro = emailpro;
        Genre = genre;
        this.idgroup = idgroup;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }



    public String getAdresse() {
        return adresse.trim();
    }

    public String getEmailPer() {
        return EmailPer.trim();
    }

    public String getEmailpro() {
        return Emailpro.trim();
    }

    public String getGenre() {
        return Genre.trim();
    }

    public String getTelephonePer() {
        return TelephonePer;
    }

    public String getTelephonPro() {
        return TelephonPro;
    }

    public int getIdgroup() {
        return idgroup;
    }

    @Override
    public String toString() {
        return "Contact {" +
                "Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Telephone1=" + TelephonePer +
                ", Telephone2=" + TelephonPro +
                ", adresse='" + adresse + '\'' +
                ", EmailPer='" + EmailPer + '\'' +
                ", Emailpro='" + Emailpro + '\'' +
                ", Genre='" + Genre + '\'' +
                '}';
    }
}
