package com.example.m_contacts.bll;

import com.example.m_contacts.bo.Contact;
import com.example.m_contacts.bo.Group;
import com.example.m_contacts.data.Contactdao;
import com.example.m_contacts.data.DataBaseException;

import java.util.ArrayList;
import java.util.List;

public class ContactManagement {

    private Contactdao CD = new Contactdao();

    private GroupManagement GM=new GroupManagement();

    /**
     *Ajouter un contact
     */

    public void AjouterContact(Contact contact) throws DataBaseException, BusinessLogicException {
        int id=CD.selectid(contact);
        if(id== -1) {
            Contact c = CD.CreateCon(contact);
        }else{
            throw new BusinessLogicException();
        }

    }



    public void addtogroup(Contact c, Group g) throws DataBaseException, BusinessLogicException {
        Group group=GM.SearchGroupWithName(g.getNom());
        if(group.equals(null)){
            throw new BusinessLogicException();
        }else{
            CD.ContactParticipation(c,group);
        }
    }


    public List<Contact> getcon() throws DataBaseException, BusinessLogicException {
        List<Contact> c=CD.getAll();
        if(c.isEmpty()){
            throw new BusinessLogicException();
        }else{
            return c;
        }
    }



    /**
     *Modifier un contact
     */


    public void ModifierContact(Contact contact) throws DataBaseException, BusinessLogicException {
        int id=CD.Find(contact);
        if(id== -1){
            throw new BusinessLogicException();
        }else {
            CD.UpdateCon(contact);
        }
    }


    /**
     *Supprimer un contact
     */



    public void SupprimerContact(Contact c) throws DataBaseException, BusinessLogicException {
        int id=CD.Find(c);
        if (id== -1) {
            throw new BusinessLogicException();
        } else {
            CD.DeleteCon(c);
        }
    }


    /**
     *Rechercher un contact par nom
     */



    public List<Contact>  RechercheNomC(String nom ) throws DataBaseException {

            return CD.FindContactbyName(nom);

    }

    /**
     *REchercher  un contact par numero  personnel
     */



    public List<Contact> RechercheNumpc1(String number) throws DataBaseException{
     return  CD.FindContactbyNumberper(number);
    }

    /**
     *Recherche un contact par numero profetionnel
     */



    public List<Contact> RechercheNumpc2(String number) throws DataBaseException {

       return  CD.FindContactbyNumberpro(number);
    }


    /**
     *lister tous les contacts
     */



    public void  ToutsContact()  throws DataBaseException {
        List<Contact> list = new ArrayList<>();
        list = CD.getAll();
        for (Object item:list) {
            System.out.println(item);
        }

    }






}
