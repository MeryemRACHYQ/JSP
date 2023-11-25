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
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;



/**
 *
 * @author LACHGAR
 */

@ManagedBean
@RequestScoped
public class ServiceBean {
    
    private Employe employee;
    private Service service;
    private ServiceService serviceService;
    private List<Service> services;
    private List<Employe> employe;
    private EmployeService employeeService;
    private static ChartModel barModel;

    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();
        employee = new Employe();
        employeeService = new EmployeService();
    }

    public List<Employe> getEmployees() {
        if (employe == null) {
            employe = service.getEmployes();
        }
        return employe;
    }

    public void setEmployees(List<Employe> employees) {
        this.employe = employe;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String onCreateAction() {
        serviceService.create(service);
        service = new Service();
        return null;
    }

    public void onDeleteAction() {
        service.setEmployes(null);
        serviceService.delete(service);
    }

    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        service.setEmployes(null);
        serviceService.update(service);
    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById((int) service.getId());
        getEmployees();
    }

    public void onCancel(RowEditEvent event) {
    }

    public void onEditm(RowEditEvent event) {
        Service editedService = (Service) event.getObject();
        editedService.setEmployes(null);
        serviceService.update(editedService);
        
        services = serviceService.getAll();
    }

    public String onDeleteActionm() {
       serviceService.delete(service);
        
        services = serviceService.getAll();
        return null;
    
    }

    public List<Employe> serviceLoad() {
        for (Employe e : employeeService.getAll()) {
            if (e.getService().equals(service)) {
                employe.add(e);
            }
        }
        return employe;
    }

    public void onCancelm(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries services = new ChartSeries();
        services.setLabel("Services");
        model.setAnimate(true);
        for (Service s : serviceService.getAll()) {
            services.set(s.getNom(), s.getEmployes().size());
        }
        model.addSeries(services);

        return model;
    }
    
}