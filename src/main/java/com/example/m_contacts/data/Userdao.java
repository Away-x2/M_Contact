package com.example.m_contacts.data;


import com.example.m_contacts.bll.BusinessLogicException;
import com.example.m_contacts.bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Userdao {

    public void create(User user) throws  DataBaseException {

        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "insert into users(password,name,email,loggin) values(?,?,?,? )";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, user.getPassword());
            stm.setString(2, user.getName());
            stm.setString(3, user.getEmail());
            stm.setString(4, "false");
            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

    }

    public List<User>  SearchUser(User user) throws  DataBaseException {
        List<User> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT * from users where password=? and name=? and email=?");
            stm.setString(1,user.getPassword());
            stm.setString(2,user.getName());
            stm.setString(3,user.getEmail());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(resulttouser(rs));
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
    public List<String> Getnameemail () throws DataBaseException,BusinessLogicException{
        List<String> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();

            String sqlInsert = "SELECT name,email FROM users WHERE loggin=\'true\'";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

        if (list.isEmpty()) {
            throw new BusinessLogicException();
        }

        return list;
    }



    public String findpassword(String name, String email) throws DataBaseException, BusinessLogicException {
        List<User> list = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();

            String sqlInsert = "SELECT * FROM users WHERE name=? AND email=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, name.trim());
            stm.setString(2, email.trim());

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                list.add(resulttouser(rs));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

        if (list.isEmpty()) {
            throw new BusinessLogicException("Password not found for the given name and email.");
        }

        return list.get(0).getPassword();
    }

    public void updateloggin( String password) throws DataBaseException{
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "update users set loggin=? where password=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, "true");
            stm.setString(2,password );
            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
    }


    public int GetCurrentUserId() throws DataBaseException {
        int id = 0;
        try {
            Connection c = dbConnection.getInstance();
            String sqlQuery = "SELECT id FROM users WHERE loggin = 'true'";
            PreparedStatement stm = c.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                return id;
            }
            rs.close();
            return id;
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
    }


    public boolean PassIsExist(String pass) throws DataBaseException {
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "select password from users where password=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return  true;
            }
            return false;

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    public boolean finduser(String name ,String pass) throws DataBaseException{
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "select user from users where password=? and name=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, pass);
            stm.setString(2,name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return  true;
            }
            return false;

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }


    public boolean AppInUse() throws DataBaseException{
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "select * from users where loggin=\'true\'";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return  true;
            }
            return false;

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

     public  void userupdatelogout() throws DataBaseException{
         try {
             Connection c = dbConnection.getInstance();
             String sqlInsert = "update users set loggin=? where loggin=\'true\'";
             PreparedStatement stm = c.prepareStatement(sqlInsert);
             stm.setString(1, "false");
             stm.executeUpdate();
         } catch (SQLException ex) {
             throw new DataBaseException(ex);
         }

    }
    public void Delete() throws DataBaseException,BusinessLogicException{
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = " delete  from contact where userid=? ; delete  from groupe where userid=? ; delete from users where loggin=\'true\'";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setInt(1,GetCurrentUserId());
            stm.setInt(2,GetCurrentUserId());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    public void updatepassword(String newpass)throws DataBaseException{
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "update users set password=? where loggin=\'true\'";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, newpass);
            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }

    }








    private User resulttouser(ResultSet rs) throws SQLException {
        return new User(rs.getString("name"),rs.getString("password"),rs.getString("email"));
    }
}
