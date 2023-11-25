/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IDao.IDao;
import entities.Employe;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hp
 */
public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(employe);
        session.getTransaction().commit();
        return true;  
    }

    @Override
    public boolean delete(Employe employe) {
 Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(int id) {
Employe employe = null;
Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
                employe = (Employe) session.get(Employe.class, id);

        return employe;

    }

    @Override
    public List<Employe> getAll() {
    List<Employe> employes = null;
     Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes = session.createQuery("from Employe").list();
session.getTransaction().commit();
        return employes;
    
    }
    
}
