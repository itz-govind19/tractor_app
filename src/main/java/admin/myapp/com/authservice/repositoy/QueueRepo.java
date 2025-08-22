package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepo  extends JpaRepository<Queue, Long> {
}
