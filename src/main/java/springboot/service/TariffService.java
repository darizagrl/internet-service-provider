package springboot.service;

import springboot.dto.TariffDTO;
import springboot.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAllTariffsByType(String type);

    Tariff tariffById(int id);

    Tariff addTariff(TariffDTO tariffDTO);

    Tariff updateTariff(int id, TariffDTO tariffDTO);

    void deleteTariff(int id);

    List<Tariff> getAllTariffs();
}
