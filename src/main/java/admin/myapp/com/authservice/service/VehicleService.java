package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.dto.VehicleDTO;
import admin.myapp.com.authservice.entity.User;
import admin.myapp.com.authservice.entity.VehicleDetail;
import admin.myapp.com.authservice.repositoy.UserRepo;
import admin.myapp.com.authservice.repositoy.VehicalDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicalDetailRepo vehicalDetailRepo;

    @Autowired
    private UserRepo userRepository;

    public VehicleDTO createVehicle(VehicleDTO dto) {
        VehicleDetail vehicle = new VehicleDetail();
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setModel(dto.getModel());

        Optional<User> userOpt = userRepository.findByUsername(dto.getUserName());
        userOpt.ifPresent(vehicle::setOwner);

        VehicleDetail saved = vehicalDetailRepo.save(vehicle);
        return mapToDTO(saved);
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicalDetailRepo.findByIsDeletedFalse()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public VehicleDTO getVehicleById(Long id) {
        Optional<VehicleDetail> opt = vehicalDetailRepo.findById(id);
        return opt.filter(v -> !v.isDeleted())
                .map(this::mapToDTO)
                .orElse(null);
    }

    public VehicleDTO updateVehicle(Long id, VehicleDTO dto) {
        Optional<VehicleDetail> opt = vehicalDetailRepo.findById(id);
        if (opt.isPresent()) {
            VehicleDetail vehicle = opt.get();
            if (vehicle.isDeleted()) return null;

            vehicle.setVehicleType(dto.getVehicleType());
            vehicle.setVehicleNumber(dto.getVehicleNumber());
            vehicle.setModel(dto.getModel());

            if (dto.getUserName() != null) {
                userRepository.findByUsername(dto.getUserName())
                        .ifPresent(vehicle::setOwner);
            }

            VehicleDetail updated = vehicalDetailRepo.save(vehicle);
            return mapToDTO(updated);
        }
        return null;
    }

    public boolean deleteVehicle(Long id) {
        Optional<VehicleDetail> opt = vehicalDetailRepo.findById(id);
        if (opt.isPresent()) {
            VehicleDetail vehicle = opt.get();
            vehicle.setDeleted(true);
            vehicalDetailRepo.save(vehicle);
            return true;
        }
        return false;
    }

    private VehicleDTO mapToDTO(VehicleDetail vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setVehicleType(vehicle.getVehicleType());
        dto.setVehicleNumber(vehicle.getVehicleNumber());
        dto.setModel(vehicle.getModel());
        if (vehicle.getOwner() != null) {
            dto.setUserName(vehicle.getOwner().getUsername());
        }
        return dto;
    }
}

