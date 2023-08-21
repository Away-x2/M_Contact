package com.example.m_contacts.bo;

public class  User{
    int id;

    String password ;
    String Name;

    String email;

    String imagepath;


    public User(String name, String password ,String email) {
        this.password = password;
        Name = name;
        this.email=email;

    }

    public String getImagepath() {
        return imagepath;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return password;
    }
}
