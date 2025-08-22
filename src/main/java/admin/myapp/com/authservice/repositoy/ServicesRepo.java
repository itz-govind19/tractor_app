package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Service;
import io.jsonwebtoken.impl.lang.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepo  extends JpaRepository<Service, Long> {
}
