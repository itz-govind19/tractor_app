package admin.myapp.com.authservice.controller;


import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.ServiceDTO;
import admin.myapp.com.authservice.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL+Constants.BASE_URL_SERVICES)
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    // Create a new service
    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO dto) {
        ServiceDTO created = servicesService.createService(dto);
        return ResponseEntity.ok(created);
    }

    // Get all active (non-deleted) services
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = servicesService.getAllServices();
        return ResponseEntity.ok(services);
    }

    // Get a specific service by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        ServiceDTO service = servicesService.getServiceById(id);
        return ResponseEntity.ok(service);
    }

    // Soft delete a service by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
