package springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dto.TariffDTO;
import springboot.entity.Tariff;
import springboot.repository.TariffRepo;
import springboot.service.TariffService;

import java.util.List;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {
    private final TariffRepo tariffRepo;

    @Autowired
    public TariffServiceImpl(TariffRepo tariffRepo) {
        this.tariffRepo = tariffRepo;
    }

    @Override
    public List<Tariff> getAllTariffsByType(String type) {
        return this.tariffRepo.findByType(type);
    }

    @Override
    public Tariff tariffById(int id) {
        Optional<Tariff> optional = tariffRepo.findById(id);
        Tariff tariff;
        if (optional.isPresent()) {
            tariff = optional.get();
        } else {
            throw new RuntimeException("Tariff not found for id :: " + id);
        }
        return tariff;
    }

    @Override
    public Tariff addTariff(TariffDTO tariffDTO) {
        Tariff tariff = new Tariff();
        tariff.setName(tariffDTO.getName());
        tariff.setDescription(tariffDTO.getDescription());
        tariff.setPrice(tariffDTO.getPrice());
        tariff.setType(tariffDTO.getType());
        return tariffRepo.save(tariff);
    }

    @Override
    public Tariff updateTariff(int id, TariffDTO tariffDTO) {
        Tariff tariff = tariffRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tariff Id:" + id));
        tariff.setName(tariffDTO.getName());
        tariff.setDescription(tariffDTO.getDescription());
        tariff.setPrice(tariffDTO.getPrice());
        tariff.setType(tariffDTO.getType());
        return tariffRepo.save(tariff);
    }

    @Override
    public void deleteTariff(int id) {
        this.tariffRepo.deleteById(id);
    }

    @Override
    public List<Tariff> getAllTariffs() {
        return tariffRepo.findAll();
    }
}
