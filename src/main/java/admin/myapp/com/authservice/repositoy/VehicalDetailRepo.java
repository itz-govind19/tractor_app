package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicalDetailRepo  extends JpaRepository<VehicleDetail, Long> {
}
