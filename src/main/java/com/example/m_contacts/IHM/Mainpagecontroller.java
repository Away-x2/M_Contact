package com.example.m_contacts.IHM;

import com.example.m_contacts.bll.BusinessLogicException;
import com.example.m_contacts.bll.ContactManagement;
import com.example.m_contacts.bll.GroupManagement;
import com.example.m_contacts.bll.Usermanagement;
import com.example.m_contacts.bo.Contact;
import com.example.m_contacts.bo.Group;
import com.example.m_contacts.data.Contactdao;
import com.example.m_contacts.data.DataBaseException;
import com.example.m_contacts.utils.VerificationUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class Mainpagecontroller {

    @FXML
    private TableColumn<Contact,Integer> groupidcontact;


    @FXML
    private AnchorPane Account;

    @FXML
    private TableColumn<Group,Integer> Groupid;

    @FXML
    private AnchorPane Managecontactpage;

    @FXML
    private AnchorPane Managegrouppage;

    @FXML
    private TableColumn<Contact,String> adress;

    @FXML
    private TextField contactadress;

    @FXML
    private TableColumn<Contact,String> contactgender;

    @FXML
    private TextField contactname;

    @FXML
    private TextField contactpranom;

    @FXML
    private TableView<Contact> contacttable;

    @FXML
    private TextField prophone;


    @FXML
    private TextField perphone;


    @FXML
    private Label emaillabel;

    @FXML
    private ChoiceBox<String> femaleormale;

    @FXML
    private AnchorPane firstpage;

    @FXML
    private TableColumn<Group,String> groupname;

    @FXML
    private TextField groupsearchkeyword;

    @FXML
    private TableView<Group> grouptable;

    @FXML
    private TableColumn<Contact,Integer> id;

    @FXML
    private Label namelabel;

    @FXML
    private TextField nameofthegroup;

    @FXML
    private TableColumn<Contact,String> nom;

    @FXML
    private TextField peremail;

    @FXML
    private TableColumn<Contact,String> peremailcontact;

    @FXML
    private TableColumn<Contact,String> perphonetable;

    @FXML
    private TableColumn<Contact,String> prenom;

    @FXML
    private TextField proemail;

    @FXML
    private TableColumn<Contact,String> proemailcontact;

    @FXML
    private ImageView profilephoto;

    @FXML
    private ImageView profilephoto2;

    @FXML
    private AnchorPane manageabout;

    @FXML
    private TableColumn<Contact,String> prophonetable;

    @FXML
    private TextField searchcontactkeyword;

    Usermanagement UM=new Usermanagement();
    GroupManagement GM=new GroupManagement();

    ContactManagement CM=new ContactManagement();

    Contactdao CD=new Contactdao();


    @FXML
    void Changepassword(ActionEvent event) {
        // Create a new stage for the password change dialog
        Stage dialogStage = new Stage();
        Image logo=new Image("C:\\Users\\admin\\Desktop\\M_Contacts\\src\\main\\resources\\com\\example\\m_contacts\\setting.png");
        dialogStage.getIcons().add(logo);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Change Password");

        // Create layout and controls for the dialog
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Create labels
        Label oldPasswordLabel = new Label("Old Password:");
        oldPasswordLabel.setStyle(" -fx-text-fill: black; -fx-font-weight: bold;");
        Label newPasswordLabel = new Label("New Password:");
        newPasswordLabel.setStyle(" -fx-text-fill: black; -fx-font-weight: bold;");
        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle(" -fx-text-fill: black; -fx-font-weight: bold;");


        PasswordField oldPasswordField = new PasswordField();
        PasswordField newPasswordField = new PasswordField();
        PasswordField confirmPasswordField = new PasswordField();

        Button changeButton = new Button("Change Password");
        changeButton.setStyle("-fx-background-color: purple;-fx-font-weight: bold;-fx-text-fill: black; ");
        changeButton.setOnAction(e -> {
            // Perform password change logic here
            String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            try {
                if (UM.PasswordIsExist(oldPassword) && VerificationUtils.verifyPassword(newPassword) &&VerificationUtils.verifyPassword(confirmPassword)) {
                    // Show success message
                    UM.newpass(confirmPassword);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("Password changed successfully.");
                    alert.showAndWait();

                    // Close the dialog
                    dialogStage.close();
                } else {
                    // Show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Password change failed. Please check your input.");
                    alert.showAndWait();
                }
            } catch (DataBaseException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add labels and fields to the gridPane
        gridPane.add(oldPasswordLabel, 0, 0);
        gridPane.add(oldPasswordField, 1, 0);
        gridPane.add(newPasswordLabel, 0, 1);
        gridPane.add(newPasswordField, 1, 1);
        gridPane.add(confirmPasswordLabel, 0, 2);
        gridPane.add(confirmPasswordField, 1, 2);
        gridPane.add(changeButton, 1, 3);

        // Set layout as the content of the dialog stage
        dialogStage.setScene(new Scene(gridPane));
        dialogStage.showAndWait();
    }




     void settext(String name,String email){
        namelabel.setText(name);
        emaillabel.setText(email);
    }




    @FXML
    void ShowAccountpage(ActionEvent event) throws BusinessLogicException, DataBaseException {
        firstpage.setVisible(false);
        Account.setVisible(true);
        Managegrouppage.setVisible(false);
        Managecontactpage.setVisible(false);
        manageabout.setVisible(false);
        List<String> list=UM.GetNameEmail();
        this.settext(list.get(0),list.get(1));

    }

    @FXML
    void deleteuseraccount(ActionEvent event) throws DataBaseException {
        // Create a confirmation dialog
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Logout");
        confirmDialog.setHeaderText("Are you sure you want to  delete your account?");
        confirmDialog.setContentText("Click OK to confirm.");

        // Show the confirmation dialog and wait for a response
        ButtonType result = confirmDialog.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                UM.DleteUser();
            } catch (DataBaseException e) {
                throw new RuntimeException(e);
            } catch (BusinessLogicException e) {
                throw new RuntimeException(e);
            }

            // Get the source of the event
            Node source = (Node) event.getSource();

            // Close the window associated with the source
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }}
    @FXML
    void logout(ActionEvent event) throws DataBaseException {
        // Create a confirmation dialog
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Logout");
        confirmDialog.setHeaderText("Are you sure you want to log out?");
        confirmDialog.setContentText("Click OK to confirm.");

        // Show the confirmation dialog and wait for a response
        ButtonType result = confirmDialog.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                UM.Updatelogout();
            } catch (DataBaseException e) {
                throw new RuntimeException(e);
            }

            // Get the source of the event
            Node source = (Node) event.getSource();

            // Close the window associated with the source
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }


    }
    void getgroups(){
       try {
           ObservableList<Group> showdata= FXCollections.observableArrayList();
           List<Group> grouplist = GM.GetAll();
           for (Group group : grouplist) {
                    showdata.add(group);

           }
           Groupid.setCellValueFactory(new PropertyValueFactory<>("id"));
           groupname.setCellValueFactory(new PropertyValueFactory<>("nom"));
           grouptable.setItems(showdata);
        } catch (DataBaseException e) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information");
           alert.setContentText("It seems there is a problem with database !");
           alert.showAndWait();
       } catch (BusinessLogicException e) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information");
           alert.setContentText("There is no created groups!");
           alert.showAndWait();         }
    }

    @FXML
    void managegroups(ActionEvent event) {
        firstpage.setVisible(false);
        Account.setVisible(false);
        Managegrouppage.setVisible(true);
        Managecontactpage.setVisible(false);
        manageabout.setVisible(false);

        getgroups();

    }

    @FXML
    void searchgroup(ActionEvent event) {
        String search = groupsearchkeyword.getText();
        if (!search.isEmpty()) {
            try {
                Group founded = GM.SearchGroupWithName(search);
                ObservableList<Group> showdata = FXCollections.observableArrayList();
                showdata.add(founded);
                Groupid.setCellValueFactory(new PropertyValueFactory<>("id"));
                groupname.setCellValueFactory(new PropertyValueFactory<>("nom"));
                grouptable.setItems(showdata);


            } catch (DataBaseException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("It seems there is a problem with database !");
                alert.showAndWait();
                       }

        }
    }
    @FXML
    void aftersearch(ActionEvent event) {
        groupsearchkeyword.clear();
        getgroups();

    }

    @FXML
    void creategroup(ActionEvent event) {
        String name=nameofthegroup.getText();
        if(!name.isEmpty()){
        Group group=new Group(name);
        try {
            GM.CreateGroup(group);
        } catch (DataBaseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("It seems there is a problem with database !");
            alert.showAndWait();
        } catch (BusinessLogicException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("This group already exist!");
            alert.showAndWait();        }

        getgroups();
        nameofthegroup.clear();

    }}

    @FXML
    void deletegroup(ActionEvent event) {
        String name=nameofthegroup.getText();
        if(!name.isEmpty()){
        try {
            GM.DeleteGroup(name);
        } catch (DataBaseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("It seems there is a problem with database !");
            alert.showAndWait();
        } catch (BusinessLogicException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("This group already deleted!");
            alert.showAndWait();        }

        getgroups();
        nameofthegroup.clear();

    }}

    void setchoisebox(){
        ObservableList<String> choices = FXCollections.observableArrayList("male", "female");
        femaleormale.setItems(choices);

    }

    @FXML
    void managecontact(ActionEvent event) {
        setchoisebox();
        Managecontactpage.setVisible(true);
        Managegrouppage.setVisible(false);
        firstpage.setVisible(false);
        Account.setVisible(false);
        manageabout.setVisible(false);
        getcantact();
        contacttable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Set UI elements to display data from the selected Contact
                contactname.setText(newValue.getNom());
                contactpranom.setText(newValue.getPrenom());
                perphone.setText(newValue.getTelephonePer());
                prophone.setText(newValue.getTelephonPro());
                contactadress.setText(newValue.getAdresse());
                peremail.setText(newValue.getEmailPer());
                proemail.setText(newValue.getEmailpro());

                // Set ChoiceBox value directly
                femaleormale.setValue(newValue.getGenre());
            } else {
                // Clear UI elements when no contact is selected
                contactname.clear();
                contactpranom.clear();
                perphone.clear();
                prophone.clear();
                contactadress.clear();
                peremail.clear();
                proemail.clear();
                femaleormale.setValue(null);
            }
        });

    }



        void getcantact(){
        try {
            ObservableList<Contact> showdata= FXCollections.observableArrayList();
            List<Contact> contactList = CM.getcon();
            for (Contact c : contactList) {
                showdata.add(c);

            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            perphonetable.setCellValueFactory(new PropertyValueFactory<>("TelephonePer"));
            prophonetable.setCellValueFactory(new PropertyValueFactory<>("TelephonPro"));
            adress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            peremailcontact.setCellValueFactory(new PropertyValueFactory<>("EmailPer"));
            proemailcontact.setCellValueFactory(new PropertyValueFactory<>("Emailpro"));
            contactgender.setCellValueFactory(new PropertyValueFactory<>("Genre"));
            groupidcontact.setCellValueFactory(new PropertyValueFactory<>("idgroup"));
            contacttable.setItems(showdata);
        } catch (DataBaseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("It seems there is a problem with database !");
            alert.showAndWait();
        } catch (BusinessLogicException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("There is no contact created !");
            alert.showAndWait();        }

    }
    @FXML
    void createcontact(ActionEvent event) throws DataBaseException,BusinessLogicException {
        String name = contactname.getText();
        String prenom = contactpranom.getText();
        String telephone1 = perphone.getText();
        String telephone2 = prophone.getText();
        String adress = contactadress.getText();
        String emailper = peremail.getText();
        String emailpro = proemail.getText();
        String gender = femaleormale.getValue();
      if(VerificationUtils.verifyName(name)&&VerificationUtils.verifyName(prenom)&&VerificationUtils.verifyNumber(telephone2)&&VerificationUtils.verifyNumber(telephone1)&&VerificationUtils.verifyEmail(emailper)&&VerificationUtils.verifyEmail(emailpro)){
          Contact contact = new Contact(name, prenom, telephone1, telephone2, adress, emailper, emailpro, gender);
          try {
              CM.AjouterContact(contact);
              getcantact();
              Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
              confirmDialog.setTitle("Add contact to group");
              confirmDialog.setHeaderText("Do you want to add this contact to an existing group?");
              confirmDialog.setContentText("Click OK to confirm.");

              ButtonType result = confirmDialog.showAndWait().orElse(ButtonType.CANCEL);

              if (result == ButtonType.OK) {
                  Stage stage=new Stage();
                  Image logo=new Image("C:\\Users\\admin\\Desktop\\M_Contacts\\src\\main\\resources\\com\\example\\m_contacts\\setting.png");
                  stage.getIcons().add(logo);
                  // Create and show a dialog to enter group name
                  Label groupNameLabel = new Label("Group Name:");
                  TextField groupNameTextField = new TextField();
                  Button add = new Button("Add to Group"); // Add text to the button
                  groupNameLabel.setStyle(" -fx-text-fill: black; -fx-font-weight: bold;");
                  add.setStyle("-fx-background-color: purple;-fx-font-weight: bold;-fx-text-fill: black; ");
                  VBox.setMargin(add, new Insets(0, 0, 0, 55   ));

                  VBox root = new VBox(10); // 10 is the spacing between elements
                  root.getChildren().addAll(groupNameLabel, groupNameTextField, add);

                  // Create a Scene
                  Scene scene = new Scene(root, 200, 100);

                  // Set the Scene and show the Stage
                  stage.setScene(scene);
                  stage.setTitle("Add contact to group");
                  stage.show(); // Wait for user input

                  add.setOnAction(e -> {
                      String groupName = groupNameTextField.getText();
                      if(!groupName.isEmpty()){
                          try {
                              Group g =GM.SearchGroupWithName(groupName);
                              CM.addtogroup(contact, g);
                              getcantact();
                              contactpranom.clear();
                              contactname.clear();
                              proemail.clear();
                              peremail.clear();
                              contactadress.clear();
                              perphone.clear();
                              prophone.clear();
                              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                              alert.setTitle("Information");
                              alert.setContentText("Operation completed successfully");
                              alert.showAndWait();
                          } catch (DataBaseException ex) {
                              ex.printStackTrace();
                              // Handle database exception
                          } catch (BusinessLogicException ex) {
                              ex.printStackTrace();
                              // Handle business logic exception
                          }
                      }
                  });
              } else if (result == ButtonType.CANCEL) {
                  contactpranom.clear();
                  contactname.clear();
                  proemail.clear();
                  peremail.clear();
                  contactadress.clear();
                  perphone.clear();
                  prophone.clear();

              }


          } catch (DataBaseException e) {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information");
              alert.setContentText("It seems there is a problem with database !");
              alert.showAndWait();
          } catch (BusinessLogicException e) {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information");
              alert.setContentText("This contact is already exist !");
              alert.showAndWait();          }
      }else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information");
          alert.setContentText("Verify your information please enter it in a valid form!");
          alert.showAndWait();
          if(!VerificationUtils.verifyName(name)){
              contactname.clear();
          } else if (!VerificationUtils.verifyName(prenom)) {
              contactpranom.clear();

          } else if (!VerificationUtils.verifyNumber(telephone2)) {
              prophone.clear();
          } else if (!VerificationUtils.verifyNumber(telephone1)) {
              perphone.clear();

          }else if (!VerificationUtils.verifyEmail(emailper)){
              peremail.clear();
          }else if (!VerificationUtils.verifyEmail(emailpro)){
              proemail.clear();
          }

      }

    }
    @FXML
    void deletecontact(ActionEvent event) {
        String name = contactname.getText();
        String prenom = contactpranom.getText();
        String telephone1 = perphone.getText();
        String telephone2 = prophone.getText();
        String adress = contactadress.getText();
        String emailper = peremail.getText();
        String emailpro = proemail.getText();
        String gender = femaleormale.getValue();
        Contact contact = new Contact(name, prenom, telephone1, telephone2, adress, emailper, emailpro, gender);
        try {
            CM.SupprimerContact(contact);
            getcantact();
        } catch (DataBaseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("It seems there is a problem with database !");
            alert.showAndWait();
        } catch (BusinessLogicException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("This contact is not founded!");
            alert.showAndWait();
        }
    }

    @FXML
    void searchcontact(ActionEvent event) {
        String search=searchcontactkeyword.getText();
        if(VerificationUtils.verifyNumber(search)) {
            List<Contact> found= null;
            try {
                found = CM.RechercheNumpc1(search);
                for (Contact c:CM.RechercheNumpc2(search)) {
                    found.add(c);
                }


                ObservableList<Contact> showdata= FXCollections.observableArrayList();
                for (Contact c : found) {
                    showdata.add(c);

                }

                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
                perphonetable.setCellValueFactory(new PropertyValueFactory<>("TelephonePer"));
                prophonetable.setCellValueFactory(new PropertyValueFactory<>("TelephonPro"));
                adress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                peremailcontact.setCellValueFactory(new PropertyValueFactory<>("EmailPer"));
                proemailcontact.setCellValueFactory(new PropertyValueFactory<>("Emailpro"));
                contactgender.setCellValueFactory(new PropertyValueFactory<>("Genre"));
                groupidcontact.setCellValueFactory(new PropertyValueFactory<>("idgroup"));
                contacttable.setItems(showdata);

            } catch (DataBaseException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("It seems there is a problem with database !");
                alert.showAndWait();
            }




        }else if (VerificationUtils.verifyName(search)){
            List<Contact> found= null;
            try {
                found =CM.RechercheNomC(search);



                ObservableList<Contact> showdata= FXCollections.observableArrayList();
                for (Contact c : found) {
                    showdata.add(c);

                }

                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
                perphonetable.setCellValueFactory(new PropertyValueFactory<>("TelephonePer"));
                prophonetable.setCellValueFactory(new PropertyValueFactory<>("TelephonPro"));
                adress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                peremailcontact.setCellValueFactory(new PropertyValueFactory<>("EmailPer"));
                proemailcontact.setCellValueFactory(new PropertyValueFactory<>("Emailpro"));
                contactgender.setCellValueFactory(new PropertyValueFactory<>("Genre"));
                groupidcontact.setCellValueFactory(new PropertyValueFactory<>("idgroup"));
                contacttable.setItems(showdata);

            } catch (DataBaseException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("It seems there is a problem with database !");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("No contact founded!");
            alert.showAndWait();
        }

    }
    @FXML
    void updatecontact(ActionEvent event) {
        String name = contactname.getText();
        String prenom = contactpranom.getText();
        String telephone1 = perphone.getText();
        String telephone2 = prophone.getText();
        String adress = contactadress.getText();
        String emailper = peremail.getText();
        String emailpro = proemail.getText();
        String gender = femaleormale.getValue();
        Contact contact = new Contact(name, prenom, telephone1, telephone2, adress, emailper, emailpro, gender);
        try {
            CM.ModifierContact(contact);
            getcantact();
        } catch (DataBaseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("It seems there is a problem with database !");
            alert.showAndWait();
        } catch (BusinessLogicException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Oops we can not do this now ! ");
            alert.showAndWait();
        }

    }
    @FXML
    void originaltableview(ActionEvent event) {
        getcantact();
    }
    @FXML
    void addcontacttogroup(ActionEvent event) {
        String name = contactname.getText();
        String prenom = contactpranom.getText();
        String telephone1 = perphone.getText();
        String telephone2 = prophone.getText();
        String adress = contactadress.getText();
        String emailper = peremail.getText();
        String emailpro = proemail.getText();
        String gender = femaleormale.getValue();
        Contact contact = new Contact(name, prenom, telephone1, telephone2, adress, emailper, emailpro, gender);
        Stage stage=new Stage();
        Image logo=new Image("C:\\Users\\admin\\Desktop\\M_Contacts\\src\\main\\resources\\com\\example\\m_contacts\\setting.png");
        stage.getIcons().add(logo);
        // Create and show a dialog to enter group name
        Label groupNameLabel = new Label("Group Name:");
        TextField groupNameTextField = new TextField();
        Button add = new Button("Add to Group"); // Add text to the button
        groupNameLabel.setStyle(" -fx-text-fill: black; -fx-font-weight: bold;");
        add.setStyle("-fx-background-color: purple;-fx-font-weight: bold;-fx-text-fill: black; ");
        VBox.setMargin(add, new Insets(0, 0, 0, 55   ));



        VBox root = new VBox(10); // 10 is the spacing between elements
        root.getChildren().addAll(groupNameLabel, groupNameTextField, add);

        // Create a Scene
        Scene scene = new Scene(root, 200, 100);

        // Set the Scene and show the Stage
        stage.setScene(scene);
        stage.setTitle("");
        stage.show(); // Wait for user input

        add.setOnAction(e -> {
            String groupName = groupNameTextField.getText();
            if(!groupName.isEmpty()){
                try {
                    Group g =GM.SearchGroupWithName(groupName);
                    CM.addtogroup(contact, g);
                    getcantact();
                    getcantact();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setContentText("Operation completed successfully");
                    alert.showAndWait();
                    stage.close();
                } catch (DataBaseException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setContentText("It seems there is a problem with database !");
                    alert.showAndWait();
                } catch (BusinessLogicException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setContentText("Oops we can not do this now! ");
                    alert.showAndWait();
                }

            }
        } );

    }
    @FXML
    void showabout(ActionEvent event) {
        manageabout.setVisible(true);
        Managegrouppage.setVisible(false);
        Managecontactpage.setVisible(false);
        firstpage.setVisible(false);
        Account.setVisible(false);


    }
    @FXML
    void Creategroupfamily(ActionEvent event) {
        Contactdao cd=new Contactdao();
        try {
            cd.CreateFamily();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Operation completed successfully");
            alert.showAndWait();
        } catch (DataBaseException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("It seems there is a problem with database !");
            alert.showAndWait();
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Oops we can not do this  now !");
            alert.showAndWait();
        }
        getgroups();


    }
}













