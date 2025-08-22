package admin.myapp.com.authservice.controller;


import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.VehicleDTO;
import admin.myapp.com.authservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL)
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Create a vehicle
    @PostMapping("createVehicle")
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO dto) {
        VehicleDTO created = vehicleService.createVehicle(dto);
        return ResponseEntity.ok(created);
    }

    // Get all vehicles
    @GetMapping("getAllVehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        VehicleDTO vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    // Update a vehicle
    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO dto) {
        VehicleDTO updated = vehicleService.updateVehicle(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // Soft delete a vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
