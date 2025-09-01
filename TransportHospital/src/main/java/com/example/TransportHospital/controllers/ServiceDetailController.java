package com.example.TransportHospital.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.TransportHospital.models.ServiceDetail;
import com.example.TransportHospital.service.ServiceDetailService;

@RestController
@RequestMapping("/api/services")
public class ServiceDetailController {

    private final ServiceDetailService serviceDetailService;

    public ServiceDetailController(ServiceDetailService serviceDetailService) {
        this.serviceDetailService = serviceDetailService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDetail>> getAllServices() {
        List<ServiceDetail> services = serviceDetailService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{serviceid}")
    public ResponseEntity<ServiceDetail> getServiceById(@PathVariable String serviceid) {
        ServiceDetail service = serviceDetailService.getServiceById(serviceid);
        if (service == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service);
    }

    @GetMapping("/vehicle/{vehiclenumber}")
    public ResponseEntity<List<ServiceDetail>> getServicesByVehicle(@PathVariable String vehiclenumber) {
        List<ServiceDetail> services = serviceDetailService.getServicesByVehicle(vehiclenumber);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<ServiceDetail>> getUpcomingServices() {
        List<ServiceDetail> services = serviceDetailService.getUpcomingServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<ServiceDetail>> getOverdueServices() {
        List<ServiceDetail> services = serviceDetailService.getOverdueServices();
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<ServiceDetail> createService(@RequestBody ServiceDetail serviceDetail) {
        ServiceDetail savedService = serviceDetailService.createService(serviceDetail);
        return ResponseEntity.ok(savedService);
    }

    @PutMapping("/{serviceid}")
    public ResponseEntity<ServiceDetail> updateService(
            @PathVariable String serviceid,
            @RequestBody ServiceDetail serviceDetail) {

        ServiceDetail updatedService = serviceDetailService.updateService(serviceid, serviceDetail);
        if (updatedService == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{serviceid}")
    public ResponseEntity<Void> deleteService(@PathVariable String serviceid) {
        boolean deleted = serviceDetailService.deleteService(serviceid);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ServiceDetail>> getServicesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<ServiceDetail> allServices = serviceDetailService.getAllServices();
        List<ServiceDetail> filteredServices = allServices.stream()
                .filter(service -> service.getNextservicedate() != null)
                .filter(service -> !service.getNextservicedate().isBefore(startDate))
                .filter(service -> !service.getNextservicedate().isAfter(endDate))
                .toList();

        return ResponseEntity.ok(filteredServices);
    }
}