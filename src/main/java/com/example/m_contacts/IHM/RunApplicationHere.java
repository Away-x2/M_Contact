package com.example.m_contacts.IHM;

import com.example.m_contacts.Mainapp;
import com.example.m_contacts.bll.BusinessLogicException;
import com.example.m_contacts.bll.Usermanagement;
import com.example.m_contacts.data.DBInstaller;
import com.example.m_contacts.data.DataBaseException;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RunApplicationHere extends Application {
    public void start(Stage primaryStage)throws BusinessLogicException, DataBaseException, IOException {
        Usermanagement u=new Usermanagement();
        try {
            if (!DBInstaller.checkIfAlreadyInstalled()) {
                DBInstaller.createDataBaseTables();

            }}catch (Exception ex){

            ex.printStackTrace();

            System.exit(-1);
        }
        if(!u.ApplicationInUse()){
            LoginGUI l=new LoginGUI();
            l.start(primaryStage);
        }else{
            Mainapp m=new Mainapp();
            m.start(primaryStage);

        }

    }
    public static void main(String[] args) {
        launch(args);
    }


}