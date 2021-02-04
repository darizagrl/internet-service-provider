package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.entity.Service;

import java.util.Optional;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    Optional<Service> findById(Integer id);
}
