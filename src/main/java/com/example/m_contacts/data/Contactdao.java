package com.example.m_contacts.data;
import com.example.m_contacts.bll.BusinessLogicException;
import com.example.m_contacts.bll.ContactManagement;
import com.example.m_contacts.bll.GroupManagement;
import com.example.m_contacts.bo.Contact;
import com.example.m_contacts.bo.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Contactdao {


    GroupManagement GM=new GroupManagement();

    public int  selectid(Contact contact) throws DataBaseException {
        int contactId = -1; // Default value if contact is not found
        try {
            Connection con = dbConnection.getInstance();
            String sqlSelect = "SELECT id FROM CONTACT WHERE nom=? and  prenom=? and telephoneper=? and  telephonepro=? and adresse=? and emailperso=? and emailprofe=? and  genre=? ";
            PreparedStatement stm = con.prepareStatement(sqlSelect);
            stm.setString(1, contact.getNom());
            stm.setString(2, contact.getPrenom());
            stm.setString(3, contact.getTelephonePer());
            stm.setString(4,contact.getTelephonPro());
            stm.setString(5, contact.getAdresse());
            stm.setString(6, contact.getEmailPer());
            stm.setString(7, contact.getEmailpro());
            stm.setString(8, contact.getGenre());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                contactId = rs.getInt("id");
                return  contactId;
            }
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        return contactId;
    }

    public int  Find(Contact contact) throws DataBaseException {
        int contactId = -1; // Default value if contact is not found
        try {
            Connection con = dbConnection.getInstance();
            String sqlSelect = "SELECT id FROM CONTACT WHERE nom=? and  prenom=? or telephoneper=? and  telephonepro=? or adresse=? and emailperso=? or emailprofe=? and  genre=? ";
            PreparedStatement stm = con.prepareStatement(sqlSelect);
            stm.setString(1, contact.getNom());
            stm.setString(2, contact.getPrenom());
            stm.setString(3, contact.getTelephonePer());
            stm.setString(4,contact.getTelephonPro());
            stm.setString(5, contact.getAdresse());
            stm.setString(6, contact.getEmailPer());
            stm.setString(7, contact.getEmailpro());
            stm.setString(8, contact.getGenre());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                contactId = rs.getInt("id");
                return  contactId;
            }
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        return contactId;
    }

    public Contact CreateCon(Contact contact) throws  DataBaseException {
        Userdao Ud=new Userdao();

        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "insert into CONTACT(nom, prenom, telephoneper, telephonepro, adresse , emailperso , emailprofe ,genre,userid) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, contact.getNom());
            stm.setString(2, contact.getPrenom());
            stm.setString(3, contact.getTelephonePer());
            stm.setString(4, contact.getTelephonPro());
            stm.setString(5, contact.getAdresse());
            stm.setString(6, contact.getEmailPer());
            stm.setString(7, contact.getEmailpro());
            stm.setString(8, contact.getGenre());
            stm.setInt(9,Ud.GetCurrentUserId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        return contact;
    }

    public void UpdateCon(Contact contact) throws  DataBaseException {

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("UPDATE CONTACT SET nom=?, prenom=?,telephoneper=?, telephonepro=?, adresse=?, emailperso=?, emailprofe=?, genre=? WHERE telephoneper=?");
            stm.setString(1, contact.getNom());
            stm.setString(2, contact.getPrenom());
            stm.setString(3, contact.getTelephonePer());
            stm.setString(4,contact.getTelephonPro());
            stm.setString(5, contact.getAdresse());
            stm.setString(6, contact.getEmailPer());
            stm.setString(7, contact.getEmailpro());
            stm.setString(8, contact.getGenre());
            stm.setString(9, contact.getTelephonePer());
            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

    }
    public List<Contact> getAll() throws  DataBaseException {
        List<Contact> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * from CONTACT order by nom");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resultToContact(rs));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        return list;

    }
    public void DeleteCon(Contact contact) throws  DataBaseException {
        int id=Find(contact);

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("DELETE from CONTACT where id=?");
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

    }




    public List<Contact>  FindContactbyName(String pnom) throws  DataBaseException {
        List<Contact> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * from Contact where upper(nom)=?");
            stm.setString(1, pnom.toUpperCase());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resultToContact(rs));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        if (list.isEmpty()) {
            return null;
        }

        return list;

    }

    public List<Contact>  FindContactbyNumberper(String number) throws  DataBaseException {
        List<Contact> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * from Contact where telephoneper=?");
            stm.setString(1, number);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resultToContact(rs));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        if (list.isEmpty()) {
            return null;
        }

        return list;

    }

    public List<Contact> FindContactbyNumberpro(String number) throws  DataBaseException {
        List<Contact> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * from Contact where telephonepro =?");
            stm.setString(1, number);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resultToContact(rs));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        if (list.isEmpty()) {
            return null;
        }

        return list;

    }
    public void ContactParticipation(Contact co, Group g) throws DataBaseException {
        int groupid=g.getId();
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "update contact set groupid=? where nom=? and  prenom=? and telephoneper=? and  telephonepro=? and adresse=? and emailperso=? and emailprofe=? and  genre=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setInt(1,groupid);
            stm.setString(2, co.getNom());
            stm.setString(3, co.getPrenom());
            stm.setString(4, co.getTelephonePer());
            stm.setString(5, co.getTelephonPro());
            stm.setString(6, co.getAdresse());
            stm.setString(7, co.getEmailPer());
            stm.setString(8, co.getEmailpro());
            stm.setString(9, co.getGenre());





            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

    }



    public void CreateFamily() throws DataBaseException, BusinessLogicException {
        List<Contact> allContacts = this.getAll();
        Map<String, Integer> nameCounts = new HashMap<>();

        for (Contact contact : allContacts) {
            String contactName = contact.getNom();
            nameCounts.put(contactName, nameCounts.getOrDefault(contactName, 0) + 1);
        }

        for (Contact contact : allContacts) {
            String contactName = contact.getNom();
            int nameCount = nameCounts.get(contactName);

            if (nameCount >= 2) {
                Group g = new Group(contactName);
                Group IsExist = GM.SearchGroupWithName(contactName);
                if (IsExist == null) {
                    GM.CreateGroup(g);
                    // Add the contact to the group you just created
                    ContactManagement cm=new ContactManagement();
                    List<Contact> cons=getwithsamename(g.getNom());
                    for (int i = 0; i < cons.size(); i++) {
                        Contact c = cons.get(i);
                            cm.addtogroup(c, g);
                     }


                }
            }
        }
    }


    public List<Contact> getwithsamename(String name) throws DataBaseException {
        List<Contact> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * FROM Contact WHERE nom=?");
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resultToContact(rs));
            }

            rs.close();
        } catch (SQLException | DataBaseException ex) {
            throw new DataBaseException(ex);
        }
        if (list.isEmpty()) {
            return null;
        }

        return list;
    }


    public List<Contact>  SoundexSearch(String word) throws DataBaseException {
        List<Contact> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * FROM Contact WHERE SOUNDEX(nom) = SOUNDEX('" + word + "')");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resultToContact(rs));
            }

            rs.close();
        } catch (SQLException | DataBaseException ex) {
            throw new DataBaseException(ex);
        }
        if (list.isEmpty()) {
            return null;
        }

        return list;

    }







    private Contact resultToContact(ResultSet rs) throws SQLException{
        return new Contact (rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("telephoneper"),rs.getString("telephonepro"),rs.getString("adresse"),rs.getString("emailperso"),rs.getString("emailprofe"),rs.getString("genre"),rs.getInt("groupid"));
    }



}


