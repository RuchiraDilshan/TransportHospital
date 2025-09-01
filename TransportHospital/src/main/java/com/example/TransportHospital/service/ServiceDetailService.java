package com.example.TransportHospital.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TransportHospital.models.ServiceDetail;
import com.example.TransportHospital.repository.ServiceDetailRepo;

@Service
public class ServiceDetailService {

    private final ServiceDetailRepo serviceDetailRepository;

    public ServiceDetailService(ServiceDetailRepo serviceDetailRepository) {
        this.serviceDetailRepository = serviceDetailRepository;
    }

    public List<ServiceDetail> getAllServices() {
        return serviceDetailRepository.findAll();
    }

    public List<ServiceDetail> getServicesByVehicle(String vehiclenumber) {
        return serviceDetailRepository.findByVehiclenumber(vehiclenumber);
    }

    public List<ServiceDetail> getUpcomingServices() {
        return serviceDetailRepository.findByNextservicedateAfter(LocalDate.now());
    }

    public ServiceDetail getServiceById(String serviceid) {
        return serviceDetailRepository.findById(serviceid).orElse(null);
    }

    public List<ServiceDetail> getOverdueServices() {
        return serviceDetailRepository.findByNextservicedateBefore(LocalDate.now());
    }

    public ServiceDetail createService(ServiceDetail serviceDetail) {
        return serviceDetailRepository.save(serviceDetail);
    }

    public ServiceDetail updateService(String serviceid, ServiceDetail serviceDetail) {
        Optional<ServiceDetail> existingService = serviceDetailRepository.findById(serviceid);
        if (existingService.isPresent()) {
            ServiceDetail serviceToUpdate = existingService.get();
            serviceToUpdate.setVehiclenumber(serviceDetail.getVehiclenumber());
            serviceToUpdate.setNextservicedate(serviceDetail.getNextservicedate());
            return serviceDetailRepository.save(serviceToUpdate);
        }
        return null;
    }

    public boolean deleteService(String serviceid) {
        if (serviceDetailRepository.existsById(serviceid)) {
            serviceDetailRepository.deleteById(serviceid);
            return true;
        }
        return false;
    }
}
