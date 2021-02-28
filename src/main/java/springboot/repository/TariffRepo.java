package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.entity.Tariff;

import java.util.List;
import java.util.Optional;

@Repository
public interface TariffRepo extends JpaRepository<Tariff, Integer> {
    Optional<Tariff> findByIdTariff(Integer id);

    List<Tariff> findByType(String type);
}
