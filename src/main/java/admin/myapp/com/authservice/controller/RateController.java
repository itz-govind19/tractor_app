package admin.myapp.com.authservice.controller;

import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.CreateRateDTO;
import admin.myapp.com.authservice.dto.RateDTO;
import admin.myapp.com.authservice.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL+Constants.BASE_URL_RATE)
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping
    public ResponseEntity<RateDTO> createRate(@RequestBody CreateRateDTO dto) {
        RateDTO created = rateService.createRate(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<RateDTO>> getAllRates() {
        return ResponseEntity.ok(rateService.getAllRates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateDTO> getRateById(@PathVariable Long id) {
        return ResponseEntity.ok(rateService.getRateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RateDTO> updateRate(@PathVariable Long id, @RequestBody CreateRateDTO dto) {
        return ResponseEntity.ok(rateService.updateRate(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
}
