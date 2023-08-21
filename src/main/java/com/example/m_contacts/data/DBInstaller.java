package com.example.m_contacts.data;


import com.example.m_contacts.utils.FileManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;


public class DBInstaller {


    public static void createDataBaseTables() throws DataBaseException {

        try {
            Connection con = dbConnection.getInstance();

            String sql = """
                  
                          
                           create table users (
                    ID INT PRIMARY KEY AUTO_INCREMENT,
                    password varchar unique,
                    name varchar,
                    email varchar,
                    loggin varchar
                    );              
                                        
                    CREATE TABLE groupe (
                      id INT PRIMARY KEY  AUTO_INCREMENT,
                      nom_du_groupe VARCHAR(255),
                      userid int,                        
                      FOREIGN KEY (userid) REFERENCES users(id)

                      
                    );
                                        
                
                    
                     CREATE TABLE CONTACT (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(255),
                        prenom VARCHAR(255),
                        telephoneper VARCHAR(255),
                        telephonepro VARCHAR(255),
                        adresse VARCHAR(255),
                        emailperso VARCHAR(255),
                        emailprofe VARCHAR(255),
                        genre VARCHAR(6) CHECK (genre IN ('male', 'female')),
                        groupid INT,
                        userid int,
                        FOREIGN KEY (groupid) REFERENCES groupe(id),
                        FOREIGN KEY (userid) REFERENCES users(id)
                    );            
                                  
                               
                    """;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }catch (Exception ex){
            throw  new DataBaseException(ex);
        }

    }

    public static boolean checkIfAlreadyInstalled() throws IOException {

        String userHomeDirectory = System.getProperty("user.home");
        Properties dbProperties = DbPropertiesLoader.loadPoperties("conf.properties");
        String dbName = dbProperties.getProperty("db.name");
        String dataBaseFile = userHomeDirectory + "\\" + dbName + ".mv.db";
        return FileManager.fileExists(dataBaseFile);

    }


}
