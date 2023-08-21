package com.example.m_contacts;

import com.example.m_contacts.IHM.Mainpagecontroller;
import com.example.m_contacts.bll.BusinessLogicException;
import com.example.m_contacts.data.DataBaseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Mainapp extends Application {
    @Override
    public void start(Stage stage) throws IOException, DataBaseException, BusinessLogicException {

        FXMLLoader fxmlLoader = new FXMLLoader(Mainapp.class.getResource("Mainpage.fxml"));
        AnchorPane root = fxmlLoader.load(); // Load the FXML and get the root node

        Mainpagecontroller controller = fxmlLoader.getController(); // Get a reference to the controller
        Image logo=new Image("C:\\Users\\admin\\Desktop\\M_Contacts\\src\\main\\resources\\com\\example\\m_contacts\\setting.png");
        stage.getIcons().add(logo);
        Scene scene = new Scene(root);
        stage.setTitle("Mainpage!");
        stage.setScene(scene);
        stage.show();
    }






    public static void main(String[] args) {
        launch();
    }
}
