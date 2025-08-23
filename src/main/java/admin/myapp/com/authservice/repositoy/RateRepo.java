package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.RateTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepo  extends JpaRepository<RateTable, Long> {
    List<RateTable> findByServiceServiceId(Long serviceServiceId);
}
