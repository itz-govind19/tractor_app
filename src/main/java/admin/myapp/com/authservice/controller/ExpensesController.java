package admin.myapp.com.authservice.controller;

import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.CreateExpenseDTO;
import admin.myapp.com.authservice.dto.ExpenseDTO;
import admin.myapp.com.authservice.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL+Constants.BASE_URL_EXPENSES)
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody CreateExpenseDTO dto) {
        return ResponseEntity.ok(expensesService.createExpense(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody CreateExpenseDTO dto) {
        return ResponseEntity.ok(expensesService.updateExpense(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expensesService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expensesService.getExpenseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expensesService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ExpenseDTO>> getByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(expensesService.getExpensesByVehicle(vehicleId));
    }

}
