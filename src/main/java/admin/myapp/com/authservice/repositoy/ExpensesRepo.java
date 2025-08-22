package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Long> {
}
