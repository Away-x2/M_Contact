package com.example.m_contacts.IHM;

import com.example.m_contacts.Mainapp;
import com.example.m_contacts.bll.BusinessLogicException;
import com.example.m_contacts.bll.Usermanagement;
import com.example.m_contacts.bo.User;
import com.example.m_contacts.data.DataBaseException;
import com.example.m_contacts.utils.VerificationUtils;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.mail.EmailException;

import java.io.IOException;

public class LoginGUI extends Application {

    private Scene loginScene;
    private Scene signUpScene;

    private  Scene forgettenpassewordscene;

    @Override
    public void start(Stage primaryStage) {

        Image logo=new Image("C:\\Users\\admin\\Desktop\\M_Contacts\\src\\main\\resources\\com\\example\\m_contacts\\setting.png");
        primaryStage.getIcons().add(logo);
        // Create text fields for username and password
        Label l = new Label("Log in system");
        Label l1 = new Label("Username :");
        TextField usernameField = new TextField();
        Label l2 = new Label("Password :");
        PasswordField passwordField = new PasswordField();
        l.setStyle("-fx-font-size: 33px; -fx-text-fill: purple; -fx-font-weight: bold;");
        l1.setStyle("-fx-font-size: 13px; -fx-text-fill: black; -fx-font-weight: bold;");
        l2.setStyle("-fx-font-size: 13px; -fx-text-fill: black; -fx-font-weight: bold;");

        // Create buttons for login and reset
        Button loginButton = new Button("Log in");
        Button resetButton = new Button("Reset");
        Button signButton = new Button("Sign up");
        loginButton.setPrefWidth(200);
        resetButton.setPrefWidth(200);
        signButton.setPrefWidth(200);
        loginButton.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
        resetButton.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
        signButton.setStyle("-fx-background-color: purple; -fx-text-fill: white;");


        // Create a hyperlink for the "Forgot Password" link
        Hyperlink forgotPasswordLink = new Hyperlink("Forgotten Password?");
        forgotPasswordLink.setStyle("-fx-font-size: 10px; -fx-text-fill: #FF1493; -fx-font-weight: bold;");
        forgotPasswordLink.setOnAction(e -> primaryStage.setScene(forgettenpassewordscene));

        // Set actions for the buttons
        loginButton.setOnAction(e -> {
            try {
                Login(usernameField,passwordField,primaryStage);
            } catch (DataBaseException | IOException | BusinessLogicException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("It seems there is a problem with database !");
                alert.showAndWait();
            }
        });
        resetButton.setOnAction(e -> {
            usernameField.clear();
            passwordField.clear();
        });

        // Create a layout pane (VBox) for the login scene
        VBox loginVBox = new VBox(10); // spacing between components
        loginVBox.getChildren().addAll(
                l,
                l1,
                usernameField,
                l2,
                passwordField,
                resetButton,
                loginButton,
                signButton,
                forgotPasswordLink
        );

        // Set margins for the components in the login VBox
        loginVBox.setPadding(new Insets(10, 40, 5, 40));
        VBox.setMargin(l, new Insets(5, 0, 7, 35));
        VBox.setMargin(loginButton, new Insets(0, 70, 0, 70));
        VBox.setMargin(resetButton, new Insets(0, 70, 0, 70));
        VBox.setMargin(signButton, new Insets(0, 70, 0, 70));

        // Create the login scene
        loginScene = new Scene(loginVBox, 350, 360);

        // Set the login scene as the initial scene
        primaryStage.setScene(loginScene);

        // Create a layout pane (VBox) for the sign-up scene
        VBox signUpVBox = new VBox(10);
        Label s=new Label("Sign Up Form");
        s.setStyle("-fx-font-size: 20px; -fx-text-fill: purple; -fx-font-weight: bold;");
        Label s1=new Label("Full Name:");
        Label s2=new Label("Email:");
        Label s3=new Label("Password:");
        Label s4 =new Label("Confirm password:");
        s1.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        s2.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        s3.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        s4.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");


        TextField Fullname=new TextField();
        TextField Email=  new TextField();
        PasswordField Password =new PasswordField();
        PasswordField Confirm=new PasswordField();


        Button Sign=new Button("Sign up");
        Button Back=new Button("Back to Log in");
        Back.setPrefWidth(120);
        Sign.setPrefWidth(120);

        Sign.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
        Back.setStyle("-fx-background-color: purple; -fx-text-fill: white;");


        signUpVBox.getChildren().addAll(
                s,
                s1,
                Fullname,
                s2,
                Email,
                s3,
                Password,
                s4,
                Confirm,
                Sign,
                Back
        );

        // Set margins for the components in the sign-up VBox
        signUpVBox.setPadding(new Insets(5, 70, 5, 70));
        VBox.setMargin(s,new Insets(0,0,0,40));
        VBox.setMargin(Sign,new Insets(0,0,0,40));
        VBox.setMargin(Back,new Insets(0,0,0,40));


        // Create the sign-up scene
        signUpScene = new Scene(signUpVBox, 350, 380);

        // Set actions for the "Sign Up" button to switch to the sign-up scene
        signButton.setOnAction(e -> primaryStage.setScene(signUpScene));

        // Set actions for the "Back to Login" button to switch back to the login scene
        ((Button) signUpVBox.getChildren().get(signUpVBox.getChildren().size() - 1)).setOnAction(e -> primaryStage.setScene(loginScene));

        //Set actions for Sign button
        Sign.setOnAction(e -> {
            try {
                SignIN(Fullname,Email,Password,Confirm,primaryStage,loginScene);
            } catch (DataBaseException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("It seems there is a problem with database !");
                alert.showAndWait();            }
        });
        VBox passeVbox = new VBox(10);
        Label ss=new Label("Take back password");
        s.setStyle("-fx-font-size: 20px; -fx-text-fill: purple; -fx-font-weight: bold;");
        Label ss1=new Label("Full name: ");
        Label ss2=new Label("Email:");
        ss.setStyle("-fx-text-fill: purple; -fx-font-weight: bold;");
        ss1.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        ss2.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        TextField Fullname1=new TextField();
        TextField Email1=  new TextField();

        Button action=new Button("send password");
        Button Back2=new Button("Back to Log in");
        Back2.setPrefWidth(120);
        action.setPrefWidth(120);

        action.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
        Back2.setStyle("-fx-background-color: purple; -fx-text-fill: white;");

        passeVbox.getChildren().addAll(
              ss,
                ss1,
                Fullname1,
                ss2,
                Email1,
                action,
                Back2
        );

        VBox.setMargin(ss,new Insets(0,0,0,40));
        VBox.setMargin(action,new Insets(0,0,0,40));
        VBox.setMargin(Back2,new Insets(0,0,0,40));



        forgettenpassewordscene=new Scene(passeVbox,200,240);
        action.setOnAction(e -> {
            takeback(Fullname1, Email1,primaryStage,loginScene);
        });
        Back2.setOnAction(e -> primaryStage.setScene(loginScene));





        primaryStage.setTitle("G.C.Management");
        primaryStage.show();
    }

   private  void takeback(TextField t,TextField t1,Stage p,Scene loginScene){
       Usermanagement UM=new Usermanagement();
       Tooltip FullNameErrorTooltip1 = new Tooltip("Full Name is required");
       Tooltip FullNameerrorTooltip2= new Tooltip("Please type a correct name");
       PauseTransition FullNameHideTooltip1 = new PauseTransition(Duration.seconds(3));
       PauseTransition FullNameHideTooltip2 = new PauseTransition(Duration.seconds(3));

       Tooltip EmailErrorTooltip1 =new Tooltip("Email is required");
       Tooltip EmailErrorTooltip2  =new Tooltip("Please type a valid email");
       PauseTransition EmailHideTooltip1 =new PauseTransition(Duration.seconds(3));
       PauseTransition EmailHideTooltip2 =new PauseTransition(Duration.seconds(3));

       if (t.getText().isEmpty()) {
           FullNameErrorTooltip1.show(t,p.getX()+8,p.getY()+110);
           FullNameHideTooltip1.play();
       }else if (!VerificationUtils.verifyName(t.getText())){
           FullNameerrorTooltip2.show(t,p.getX()+8,p.getY()+110);
           FullNameHideTooltip2.play();
       } else if (t1.getText().isEmpty()) {
           EmailErrorTooltip1.show(t1,p.getX()+8,p.getY()+168);
           EmailHideTooltip1.play();

       } else if (!VerificationUtils.verifyEmail(t1.getText())) {
           EmailErrorTooltip2.show(t1,p.getX()+8,p.getY()+168);
           EmailHideTooltip2.play();

       }
       FullNameHideTooltip1.setOnFinished(event -> FullNameErrorTooltip1.hide());
       FullNameHideTooltip2.setOnFinished(event ->FullNameerrorTooltip2.hide());
       EmailHideTooltip1.setOnFinished(event->EmailErrorTooltip1.hide());
       EmailHideTooltip2.setOnFinished(event->EmailErrorTooltip2.hide());

       if(VerificationUtils.verifyName(t.getText())&&VerificationUtils.verifyEmail(t1.getText())){
           try {
               try {
                   UM.FindPass(t.getText(),t1.getText());
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information");
                   alert.setContentText("Operation completed successfully verify email and log in ");
                   alert.showAndWait();
                   p.setScene(loginScene);
               } catch (EmailException e) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information");
                   alert.setContentText("It seems there is a problem Verify you address email and the network please !");
                   alert.showAndWait();               }
           } catch (DataBaseException ex) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Information");
               alert.setContentText("It seems there is a problem with database !");
               alert.showAndWait();
           } catch (BusinessLogicException ex) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Information");
               alert.setContentText("Oops we did not find this user !");
               alert.showAndWait();
           }
       }

   }

    private void Login(TextField usernameField, PasswordField passwordField,Stage p) throws DataBaseException, IOException, BusinessLogicException {
        Tooltip usernameErrorTooltip = new Tooltip("Username is required");
        PauseTransition usernameHidetooltip =new PauseTransition(Duration.seconds(3));

        Tooltip passwordErrorTooltip =new Tooltip("Password is required");
        PauseTransition passwordHideTooltipo=new PauseTransition(Duration.seconds(3));

        if(usernameField.getText().isEmpty()){
            usernameErrorTooltip.show(usernameField,p.getX()+50,p.getY()+166);
            usernameHidetooltip.play();

        } else if (passwordField.getText().isEmpty()) {
            passwordErrorTooltip.show(passwordField,p.getX()+50,p.getY()+230);
            passwordHideTooltipo.play();

        }
        passwordHideTooltipo.setOnFinished(event->passwordErrorTooltip.hide());
        usernameHidetooltip.setOnFinished(event->usernameErrorTooltip.hide());



      if(!passwordField.getText().isEmpty()&&!usernameField.getText().isEmpty()){
          if(VerificationUtils.verifyName(usernameField.getText())&&VerificationUtils.verifyPassword(passwordField.getText())){
              Usermanagement UM=new Usermanagement();
              if(UM.UserIsExist(usernameField.getText(),passwordField.getText())){
                  UM.Update(passwordField.getText());
                  // Close the current stage
                  p.close();

                  // Show the main page
                  Mainapp main=new Mainapp();
                  main.start(new Stage());


              }else{
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Information");
                  alert.setContentText(" User not founded !");
                  alert.showAndWait();

              }


          }else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information");
              alert.setContentText(" Verify  your informations password or username is not valid");
              alert.showAndWait();
          }
      }
    }

    public void SignIN(TextField Fullname,TextField Email,PasswordField Password,PasswordField Confirm ,Stage p,Scene login) throws DataBaseException {
        Usermanagement UM=new Usermanagement();
        Tooltip FullNameErrorTooltip1 = new Tooltip("Full Name is required");
        Tooltip FullNameerrorTooltip2= new Tooltip("Please type a correct name");
        PauseTransition FullNameHideTooltip1 = new PauseTransition(Duration.seconds(3));
        PauseTransition FullNameHideTooltip2 = new PauseTransition(Duration.seconds(3));

        Tooltip EmailErrorTooltip1 =new Tooltip("Email is required");
        Tooltip EmailErrorTooltip2  =new Tooltip("Please type a valid email");
        PauseTransition EmailHideTooltip1 =new PauseTransition(Duration.seconds(3));
        PauseTransition EmailHideTooltip2 =new PauseTransition(Duration.seconds(3));

        Tooltip PasswordErrorTooltip1 =new Tooltip("Password is required");
        Tooltip PasswordErrorTooltip2 =new Tooltip("Please enter a valid password that contain letters,symbols and numbers");
        PauseTransition PasswordHideTooltip1 =new PauseTransition(Duration.seconds(3));
        PauseTransition PasswordHideTooltip2 =new PauseTransition(Duration.seconds(3));
        Tooltip PasswordErrorTooltip3 =new Tooltip("This password is already used by  someone else");
        PauseTransition PasswordHideTooltip3 =new PauseTransition(Duration.seconds(3));



        Tooltip ConfirmErrorTooltip =new Tooltip("type the same password you enter above");
        PauseTransition ConfirmHideTooltip =new PauseTransition(Duration.seconds(3));



        if (Fullname.getText().isEmpty()) {
            FullNameErrorTooltip1.show(Fullname,p.getX()+60,p.getY()+130);
            FullNameHideTooltip1.play();
        }else if (!VerificationUtils.verifyName(Fullname.getText())){
            FullNameerrorTooltip2.show(Fullname,p.getX()+60,p.getY()+130);
            FullNameHideTooltip2.play();
        } else if (Email.getText().isEmpty()) {
            EmailErrorTooltip1.show(Email,p.getX()+60,p.getY()+190);
            EmailHideTooltip1.play();

        } else if (!VerificationUtils.verifyEmail(Email.getText())) {
            EmailErrorTooltip2.show(Email,p.getX()+60,p.getY()+190);
            EmailHideTooltip2.play();

        } else if (Password.getText().isEmpty()) {
            PasswordErrorTooltip1.show(Password,p.getX()+60,p.getY()+250);
            PasswordHideTooltip1.play();

        } else if (!VerificationUtils.verifyPassword(Password.getText())) {
            PasswordErrorTooltip2.show(Password,p.getX()+60,p.getY()+250);
            PasswordHideTooltip2.play();

        } else if (UM.PasswordIsExist(Password.getText())) {
            PasswordErrorTooltip3.show(Password,p.getX()+60,p.getY()+250);
            PasswordHideTooltip3.play();

        } else if (!Confirm.getText().equals(Password.getText())) {
            ConfirmErrorTooltip.show(Confirm,p.getX()+60,p.getY()+320);
            ConfirmHideTooltip.play();

        }
        FullNameHideTooltip1.setOnFinished(event -> FullNameErrorTooltip1.hide());
        FullNameHideTooltip2.setOnFinished(event ->FullNameerrorTooltip2.hide());
        EmailHideTooltip1.setOnFinished(event->EmailErrorTooltip1.hide());
        EmailHideTooltip2.setOnFinished(event->EmailErrorTooltip2.hide());
        PasswordHideTooltip1.setOnFinished(event->PasswordErrorTooltip1.hide());
        PasswordHideTooltip2.setOnFinished(event->PasswordErrorTooltip2.hide());
        ConfirmHideTooltip.setOnFinished(event->ConfirmErrorTooltip.hide());
        PasswordHideTooltip3.setOnFinished(event->PasswordErrorTooltip3.hide());

        if(Password.getText().equals(Confirm.getText())){
            String username =Fullname.getText();
            String email =Email.getText();
            String password =Password.getText();
            if(VerificationUtils.verifyName(username) && VerificationUtils.verifyEmail(email) && VerificationUtils.verifyPassword(password)){
                User user=new User(username,password,email);
                try {
                    UM.CreateUser(user);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setContentText("Operation completed successfully log in now");
                    alert.showAndWait();
                    p.setScene(login);
                } catch (BusinessLogicException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setContentText("This user is already signed in  ! ");

                    // Show the alert dialog
                    alert.showAndWait();
                }

            }


        }
    }
    public static void main(String[] args) {
        launch(args);
    }



}
