package com.example.m_contacts.bll;


import com.example.m_contacts.bo.User;
import com.example.m_contacts.data.DataBaseException;
import com.example.m_contacts.data.EmailSender;
import com.example.m_contacts.data.Userdao;
import org.apache.commons.mail.EmailException;

import java.util.ArrayList;
import java.util.List;

public class Usermanagement {

    Userdao UD=new Userdao();




    /**
     * Ajouter un utilisateur
     */
    public void CreateUser(User user) throws DataBaseException, BusinessLogicException {
        List<User> listrecherche = new ArrayList<User>();
        listrecherche=UD.SearchUser(user);
        if (listrecherche!=null ) {
            throw new BusinessLogicException("this user alredy created");
        } else {
            UD.create(user);
        }
    }

    /**
     * recherche un user
     */


    public boolean SearchForUser(User user) throws DataBaseException {
        List<User> userFound = UD.SearchUser(user);
        if( userFound==null){
            return false;
        }
        return true;

    }



    /**
     * recuperer password
     */



    public void FindPass(String name,String email) throws DataBaseException, BusinessLogicException, EmailException {
        try{
            String password =UD.findpassword(name,email);
            EmailSender es=new EmailSender();
            String message = "Dear User,\n\nWe would like to provide you with the essential information for accessing your account securely. Your unique password has been generated and is as follows:\n\nPassword: " + password + "\n\nWe strongly recommend that you keep this password in a safe and confidential place. It's crucial to prioritize the security of your account to prevent unauthorized access and potential risks to your personal information.\n\nIf you have any questions or concerns about your account's security, please don't hesitate to reach out to our dedicated support team. They are available to assist you with any inquiries you may have.\n\nThank you for choosing our services and for taking the necessary steps to ensure the protection of your account.\n\nBest regards,\nThe Contact Group management Team";

            es.SendEmail(message,email);
        }catch (DataBaseException ex ){
            System.out.println(("sorry I can't find a password with this information verify please!!!"));

        }
    }

    /**
     * trouver le id du courant utilisateur
     */

    public int GetUid() throws DataBaseException {
        int id=UD.GetCurrentUserId();
        return id;
    }

    /**
     * Verfier si un mot de passe deja exist
     */
    public boolean PasswordIsExist (String pass) throws DataBaseException {
        return UD.PassIsExist(pass);
    }

    /**
     * Verfier si un utilisateur de passe deja exist
     */

    public boolean UserIsExist(String name,String pass) throws DataBaseException {
        return UD.finduser(name,pass);
    }

    /**
     * Mise a jour le loggin
     */

    public  void Update(String pass) throws DataBaseException {
        UD.updateloggin(pass);
    }

    /**
     * Verifiez si l'application est deja ouverte
     */

    public  boolean ApplicationInUse() throws DataBaseException {
        return   UD.AppInUse();
    }
/**
 * mise a jour le loggin
 */
        public  void Updatelogout() throws DataBaseException {
           UD.userupdatelogout();
}
     public List<String> GetNameEmail() throws BusinessLogicException, DataBaseException {
         return UD.Getnameemail();
     }


         public void DleteUser() throws BusinessLogicException, DataBaseException {
            UD.Delete() ;
        }

        public void newpass(String pass)throws DataBaseException{
            try {
                UD.updatepassword(pass);
            } catch (DataBaseException e) {
                throw new RuntimeException(e);
            }
        }



}