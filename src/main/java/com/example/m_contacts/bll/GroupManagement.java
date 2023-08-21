package com.example.m_contacts.bll;


import com.example.m_contacts.bo.Group;
import com.example.m_contacts.data.DataBaseException;
import com.example.m_contacts.data.Groupdao;

import java.util.List;

public class GroupManagement {

    private Groupdao GD = new Groupdao();


    /**
     * Ajouter groupe
     */
    public void CreateGroup(Group group) throws DataBaseException, BusinessLogicException {
        Group IsExist=GD.GroupIsExist(group.getNom());
        if (IsExist!= null) {
            throw new BusinessLogicException("This group is already created");
        } else {
            GD.CreateGroup(group);
        }

    }


    /**
     *Rechercher un group
     */





    public Group SearchGroupWithName(String nom) throws DataBaseException{
        Group g=GD.GroupIsExist(nom);
            return g;
    }


    /**
     *Supprimer un groupe
     */

    public void DeleteGroup(String nom) throws DataBaseException, BusinessLogicException {
        Group IsExist=GD.GroupIsExist(nom);
        if (IsExist == null) {
            throw new BusinessLogicException("this group is not found");
        } else {
            GD.DeleteGroup(nom);
        }

    }

    /**
     * lister touts les groupes
     */

    public List<Group> GetAll() throws DataBaseException, BusinessLogicException {
        List<Group> FoundedGroups=GD.getallgroupes();
        if(FoundedGroups.isEmpty()){
            throw new BusinessLogicException("there is no created groups");
        }else{
           return FoundedGroups;

            }
        }
    }


