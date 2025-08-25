
package admin.myapp.com.authservice.service;


import admin.myapp.com.authservice.dto.ServiceDTO;
import admin.myapp.com.authservice.entity.Service;
import admin.myapp.com.authservice.entity.VehicleDetail;
import admin.myapp.com.authservice.repositoy.ServicesRepo;

import admin.myapp.com.authservice.repositoy.VehicalDetailRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService {

    @Autowired
    private ServicesRepo servicesRepo;

    @Autowired
    private VehicalDetailRepo vehicleDetailRepo;

    public ServiceDTO createService(ServiceDTO dto) {
        VehicleDetail vehicle = vehicleDetailRepo.findByVehicleIdAndIsDeletedFalse(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        Service service = new Service();
        service.setServiceName(dto.getServiceName());
        service.setDescription(dto.getDescription());
        service.setVehicle(vehicle);

        return convertToDTO(servicesRepo.save(service));
    }

    public ServiceDTO updateService(Long id,ServiceDTO dto) {
        VehicleDetail vehicle = vehicleDetailRepo.findByVehicleIdAndIsDeletedFalse(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        Optional<Service> byServiceIdAndIsDeletedFalse = servicesRepo.findByServiceIdAndIsDeletedFalse(id);
        if (byServiceIdAndIsDeletedFalse.isPresent()) {
            Service service = byServiceIdAndIsDeletedFalse.get();
            service.setServiceName(dto.getServiceName());
            service.setDescription(dto.getDescription());
            service.setVehicle(vehicle);
            return convertToDTO(servicesRepo.save(service));
        }else {
            throw new EntityNotFoundException("Vehicle not found");
        }
    }

    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> collect = servicesRepo.findByIsDeletedFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        for (ServiceDTO serviceDTO : collect) {
            Optional<VehicleDetail> byId = vehicleDetailRepo.findById(serviceDTO.getVehicleId());
            serviceDTO.setVehicleName(byId.get().getModel());
        }
        return collect;
    }

    public ServiceDTO getServiceById(Long id) {
        return servicesRepo.findByServiceIdAndIsDeletedFalse(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
    }

    public void deleteService(Long id) {
        Service service = servicesRepo.findByServiceIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
        service.setDeleted(true);
        servicesRepo.save(service);
    }

    private ServiceDTO convertToDTO(Service service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setServiceId(service.getServiceId());
        dto.setServiceName(service.getServiceName());
        dto.setDescription(service.getDescription());
        dto.setVehicleId(service.getVehicle().getVehicleId());
        return dto;
    }
}

