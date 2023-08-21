module com.example.m_contacts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires log4j;
    requires commons.email;
    requires java.mail;


    opens com.example.m_contacts to javafx.fxml;
    exports com.example.m_contacts;
    exports com.example.m_contacts.IHM;
    opens com.example.m_contacts.IHM to javafx.fxml;
    opens com.example.m_contacts.bo to javafx.base;
}