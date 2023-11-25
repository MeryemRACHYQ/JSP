/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Services.EmployeService;
import Services.ServiceService;
import entities.Employe;
import entities.Service;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author hp
 */
@ManagedBean
@RequestScoped
public class EmployeBean {

    private Employe employe;
    private Service service;
     private ServiceService serviceService;
     
    private List<Employe> employes;
    private EmployeService employeService;
    private Employe selectedChef;

    public EmployeBean() {
        employe = new Employe();
        employe.setService(new Service());
        employes = new ArrayList<>();
        employeService = new EmployeService();
        serviceService = new ServiceService();
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public Employe getSelectedChef() {
        return selectedChef;
    }

    public void setSelectedChef(Employe selectedChef) {
        this.selectedChef = selectedChef;
    }
    

    public void onCreateAction() {
        employeService.create(employe);
        employe = new Employe();
    }
    public String onDeleteAction() {
        employeService.delete(employeService.getById((int) employe.getId()));
        return null;
    }
     public List<Employe> serviceLoad() {
        for (Employe e : employeService.getAll()) {
            if (e.getService().equals(service)) {
                employes.add(e);
            }
        }
        return employes;
    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById((int) service.getId());
        getEmployes();
    }

    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
}
