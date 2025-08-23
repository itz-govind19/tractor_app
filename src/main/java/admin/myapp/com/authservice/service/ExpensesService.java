package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.dto.CreateExpenseDTO;
import admin.myapp.com.authservice.dto.ExpenseDTO;
import admin.myapp.com.authservice.entity.Expenses;
import admin.myapp.com.authservice.entity.User;
import admin.myapp.com.authservice.entity.VehicleDetail;
import admin.myapp.com.authservice.repositoy.ExpensesRepo;
import admin.myapp.com.authservice.repositoy.UserRepo;
import admin.myapp.com.authservice.repositoy.VehicalDetailRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepo expensesRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VehicalDetailRepo vehicleDetailRepo;


    public ExpenseDTO createExpense(CreateExpenseDTO dto) {
        User user = userRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        VehicleDetail vehicle = vehicleDetailRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        Expenses expense = new Expenses();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setOwner(user);
        expense.setVehicleDetail(vehicle);

        return convertToDTO(expensesRepo.save(expense));
    }


    public ExpenseDTO updateExpense(Long id, CreateExpenseDTO dto) {
        Expenses expense = expensesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

        User user = userRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        VehicleDetail vehicle = vehicleDetailRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setOwner(user);
        expense.setVehicleDetail(vehicle);

        return convertToDTO(expensesRepo.save(expense));
    }

    public List<ExpenseDTO> getAllExpenses() {
        return expensesRepo.findByIsDeletedFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO getExpenseById(Long id) {
        Expenses expense = expensesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));
        return convertToDTO(expense);
    }

    public void deleteExpense(Long id) {
        Expenses expense = expensesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));
        expense.setDeleted(true); // Soft delete
        expensesRepo.save(expense);
    }

    public List<ExpenseDTO> getExpensesByVehicle(Long vehicleId) {
        return expensesRepo.findByVehicleDetail_VehicleIdAndIsDeletedFalse(vehicleId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ExpenseDTO convertToDTO(Expenses expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setExpenseId(expense.getExpenseId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setOwnerId(expense.getOwner().getUserId());
        dto.setVehicleId(expense.getVehicleDetail().getVehicleId());
        dto.setOwnerId(expense.getOwner() != null ? expense.getOwner().getUserId() : null);
        return dto;
    }
}
