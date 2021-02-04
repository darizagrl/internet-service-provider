package springboot.service;

import org.springframework.data.domain.Page;
import springboot.dto.TariffDTO;
import springboot.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAllTariffs();
    List<Tariff> getAllTariffsByType(String type);

    Tariff tariffById(int id);

    Tariff addTariff(TariffDTO tariffDTO);

    void deleteTariff(int id);

    Page<Tariff> findPaginated(int pageNo, int pageSize);
}
