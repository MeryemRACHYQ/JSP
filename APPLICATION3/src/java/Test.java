
import ma.projet.service.EmployeeService;
import ma.projet.util.HibernateUtil;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Khol
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        HibernateUtil.getSessionFactory().openSession();
        EmployeeService e = new EmployeeService();
        
        System.out.println(e.getChefs());

    }
}
