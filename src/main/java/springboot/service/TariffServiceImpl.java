package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springboot.dto.TariffDTO;
import springboot.entity.Tariff;
import springboot.repository.TariffRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffRepo tariffRepo;

    @Override
    public List<Tariff> getAllTariffs() {
        return this.tariffRepo.findAll();
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
    public void deleteTariff(int id) {
        this.tariffRepo.deleteById(id);
    }

    @Override
    public Page<Tariff> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.tariffRepo.findAll(pageable);
    }
}
