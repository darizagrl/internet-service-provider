package springboot.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger = LogManager.getLogger(TariffSubscribingService.class);
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
        if (user.isBlocked()) {
            logger.error("Your account is blocked. Top up your balance if you want to subscribe.");
        } else {
            if (user.getBalance() < tariff.getPrice()) {
                logger.error("Insufficient funds.");
                user.setBlocked(true);
            }
            user.setBalance(user.getBalance() - tariff.getPrice());
            user.getTariffs().add(tariff);
            userRepo.save(user);
        }
    }

    @Transactional
    public void unsubscribe(Integer tariffId, String userEmail) {
        Tariff tariff = tariffRepo.findByIdTariff(tariffId).orElseThrow(NoSuchElementException::new);
        User user = userRepo.findByEmail(userEmail).orElseThrow(NoSuchElementException::new);
        user.getTariffs().remove(tariff);
        userRepo.save(user);
    }
}
