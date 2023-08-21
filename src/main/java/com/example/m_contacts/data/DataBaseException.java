package com.example.m_contacts.data;

public class DataBaseException extends Exception{
    public DataBaseException(){
        super("Erreur base de données");
    }

    public DataBaseException(Throwable ex){
        super(ex);
    }

    public DataBaseException(String message) {
        super(message);
    }
}
