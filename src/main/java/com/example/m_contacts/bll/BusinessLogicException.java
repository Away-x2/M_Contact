package com.example.m_contacts.bll;

public class BusinessLogicException extends Exception{
    public BusinessLogicException(String message) {
        System.out.println(message);
    }
    public BusinessLogicException (){};
}
