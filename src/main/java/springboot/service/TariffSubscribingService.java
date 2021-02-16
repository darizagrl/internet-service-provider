package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.Tariff;
import springboot.entity.User;
import springboot.repository.TariffRepo;
import springboot.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class TariffSubscribingService {
    private final TariffRepo tariffRepo;
    private final UserRepo userRepo;

    @Autowired
    public TariffSubscribingService(TariffRepo tariffRepo, UserRepo userRepo) {
        this.tariffRepo = tariffRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void subscribe(Integer tariffId, String userEmail) {
        Tariff tariff = tariffRepo.findByIdTariff(tariffId)
                .orElseThrow(NoSuchElementException::new);
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(NoSuchElementException::new);
        if (user.getBalance() < tariff.getPrice()) {
            throw new RuntimeException("Insufficient funds");
        }
        user.setBalance(user.getBalance() - tariff.getPrice());
        user.getTariffs().add(tariff);
        userRepo.save(user);
    }

    @Transactional
    public void unsubscribe(Integer tariffId, String userEmail) {
        Tariff tariff = tariffRepo.findByIdTariff(tariffId).orElseThrow(NoSuchElementException::new);
        User user = userRepo.findByEmail(userEmail).orElseThrow(NoSuchElementException::new);
        user.getTariffs().remove(tariff);
        userRepo.save(user);
    }
}
