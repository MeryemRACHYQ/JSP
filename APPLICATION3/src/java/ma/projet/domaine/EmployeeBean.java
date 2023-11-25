/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;


import java.util.List;
import javax.faces.bean.ManagedBean;

import ma.projet.beans.Employee;
import ma.projet.beans.Service;
import ma.projet.service.EmployeeService;
import ma.projet.service.ServiceService;

import org.primefaces.event.RowEditEvent;



/**
 *
 * @author LACHGAR
 */
@ManagedBean(name = "employeeBean")
public class EmployeeBean {

    private Employee employee;

    private Service service;
    private Employee chef;
    private List<Employee> employees;
    private List<Employee> chefs;
    private EmployeeService employeeService;
    private ServiceService serviceService;

    public EmployeeBean() {
        employee = new Employee();
        employeeService = new EmployeeService();
        serviceService = new ServiceService();
        chefs = employeeService.getChefs();
        chef = new Employee();

    }

    public List<Employee> getEmployees() {
        if (employees == null) {
            employees = employeeService.getAll();
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getChef() {
        return chef;
    }

    public void setChef(Employee chef) {
        this.chef = chef;
    }

    public List<Employee> getChefs() {
        if (chefs == null) {
            chefs = employeeService.getChefs();
        }
        return chefs;
    }

    public void setChefs(List<Employee> chefs) {
        this.chefs = chefs;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String onCreateAction() {
//        
//        if (chef != null) {
//            
//            employee.setChef(null);
//        } else {
//            
//            employee.setChef(chef);
//            
//        }
//
//        employeeService.create(employee);
//        employee = new Employee();
//        chef = new Employee();
//        return null;

        if (chef != null) {
            // Retrieve the chef from the database based on some criteria
            Employee persistedChef = employeeService.getById(chef.getId());
            employee.setChef(persistedChef);
        } else {
            employee.setChef(null);
        }

        employeeService.create(employee);
        employee = new Employee();
        chef = new Employee();
        return null;
    }

    public String onDeleteAction() {

        employeeService.delete(employeeService.getById(employee.getId()));
        return null;
    }

    public List<Employee> serviceLoad() {
        for (Employee m : employeeService.getAll()) {
            if (m.getService().equals(service)) {
                employees.add(m);
            }
        }
        return employees;

    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        getEmployees();
    }

    public void onEdit(RowEditEvent event) {
        employee = (Employee) event.getObject();
        Service service = serviceService.getById(this.employee.getService().getId());
        employee.setService(service);
        employee.getService().setNom(service.getNom());
        employeeService.update(employee);

    }

    public void onCancel(RowEditEvent event) {
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }



}
