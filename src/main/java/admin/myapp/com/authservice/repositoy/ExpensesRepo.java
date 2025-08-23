package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Long> {
    List<Expenses> findByIsDeletedFalse();

    List<Expenses> findByVehicleDetail_VehicleIdAndIsDeletedFalse(Long vehicleId);

}
