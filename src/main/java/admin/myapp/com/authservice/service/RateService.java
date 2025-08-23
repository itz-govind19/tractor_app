package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.dto.CreateRateDTO;
import admin.myapp.com.authservice.dto.RateDTO;
import admin.myapp.com.authservice.entity.RateTable;
import admin.myapp.com.authservice.entity.Service;
import admin.myapp.com.authservice.repositoy.RateRepo;
import admin.myapp.com.authservice.repositoy.ServicesRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class RateService {

    @Autowired
    private RateRepo rateRepo;

    @Autowired
    private ServicesRepo servicesRepo;

    public RateDTO createRate(CreateRateDTO dto) {
        Service service = servicesRepo.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        RateTable rate = new RateTable();
        rate.setUnitType(dto.getUnitType());
        rate.setSubUnitType(dto.getSubUnitType());
        rate.setRateAmount(dto.getRateAmount());
        rate.setService(service);

        return convertToDTO(rateRepo.save(rate));
    }

    public List<RateDTO> getAllRates() {
        return rateRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RateDTO getRateById(Long id) {
        RateTable rate = rateRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));
        return convertToDTO(rate);
    }

    public RateDTO updateRate(Long id, CreateRateDTO dto) {
        RateTable rate = rateRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));

        Service service = servicesRepo.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        rate.setUnitType(dto.getUnitType());
        rate.setRateAmount(dto.getRateAmount());
        rate.setService(service);

        return convertToDTO(rateRepo.save(rate));
    }

    public void deleteRate(Long id) {
        RateTable rate = rateRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));
        rateRepo.delete(rate);
    }

    private RateDTO convertToDTO(RateTable rate) {
        RateDTO dto = new RateDTO();
        dto.setRateId(rate.getRateId());
        dto.setUnitType(rate.getUnitType());
        dto.setSubUnit(rate.getSubUnitType());
        dto.setRateAmount(rate.getRateAmount());
        dto.setServiceId(rate.getService().getServiceId());
        dto.setServiceName(rate.getService().getServiceName());
        return dto;
    }
}
