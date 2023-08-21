package com.example.m_contacts.data;

import com.example.m_contacts.bo.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Groupdao {
    Userdao UD=new Userdao();
    public void CreateGroup(Group group) throws DataBaseException {

        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "insert into groupe(nom_du_groupe,userid) values(?,?)";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, group.getNom());
            stm.setInt(2,UD.GetCurrentUserId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
    }


    public void DeleteGroup(String nom) throws DataBaseException {

        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "delete from groupe where nom_du_groupe=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, nom);


            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
    }


    public Group GroupIsExist(String nom) throws DataBaseException {
        try {
            Connection c = dbConnection.getInstance();
            String sqlInsert = "select * from groupe where nom_du_groupe=?";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            stm.setString(1, nom);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return new Group(rs.getInt("id"), rs.getString("nom_du_groupe"));
                }
            }


        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        return null;
    }

    public List<Group> getallgroupes() throws DataBaseException {
        List<Group> groupes = new ArrayList<>();

        try {
            Connection c = dbConnection.getInstance();
            java.lang.String sqlInsert = "select * from groupe ;";
            PreparedStatement stm = c.prepareStatement(sqlInsert);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                groupes.add(resultToGroup(rs));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
        return groupes;
    }





    private  Group resultToGroup(ResultSet rs) throws SQLException {
        String nom=rs.getString("nom_du_groupe");
        int id=rs.getInt("id");
        return new Group(id,nom);
    }



}
