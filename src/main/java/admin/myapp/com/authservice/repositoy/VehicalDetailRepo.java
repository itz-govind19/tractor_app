package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicalDetailRepo  extends JpaRepository<VehicleDetail, Long> {

    List<VehicleDetail> findByIsDeletedFalse();


}
