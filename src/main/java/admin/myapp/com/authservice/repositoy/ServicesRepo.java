package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesRepo  extends JpaRepository<Service, Long> {

    Optional<Service> findByServiceIdAndIsDeletedFalse(Long serviceId); // ✅ Fixed

    List<Service> findByIsDeletedFalse();
}
