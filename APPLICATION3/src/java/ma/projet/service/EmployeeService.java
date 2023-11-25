/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Employee;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Khol
 */
public class EmployeeService implements IDao<Employee>{

    @Override
    public boolean create(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
        //prime forces
    }

    @Override
    public boolean update(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employee getById(int id) {
        Employee employee  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employee  = (Employee) session.get(Employee.class, id);
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List <Employee> employees = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employees  = session.createQuery("from Employee").list();
        session.getTransaction().commit();
        return employees;
    }
    
    
    public List<Object[]> nbemployee(){
        List<Object[]> employees = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employees  = session.createQuery("select count(e), e.nom, e.prenom, e.dateNaissance, e.photo from Employee e group by e.nom, e.prenom, e.dateNaissance, e.photo").list();
        session.getTransaction().commit();
        return employees;
    }
    
//    public List<Employee> getbydates(Date d1 , Date d2){
//        List <Employee> employees = new ArrayList<>();
//        Session session  = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//         employees  = session.createQuery("from Employee m where m.dateAchat between :d1 and :d2").setParameter("d1", d1).setParameter("d2", d2).list();
//        session.getTransaction().commit();
//        return employees;
//        
//    }
    
    public List<Employee> getChefs() {
        List <Employee> chefs = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        chefs  = session.createQuery("from Employee e WHERE e.chef is null").list();
        session.getTransaction().commit();
        return chefs;
    }
}
