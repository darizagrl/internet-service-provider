package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.Account;
import springboot.entity.Tariff;
import springboot.entity.User;
import springboot.repository.AccountRepo;
import springboot.repository.TariffRepo;
import springboot.repository.UserRepo;

import javax.transaction.Transactional;

@Service
public class TariffSubscribingService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TariffRepo tariffRepo;
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public Tariff subscribe(Integer tariffId, String userEmail){
        Tariff tariff = tariffRepo.findByIdTariff(tariffId).orElseThrow(RuntimeException::new);
        User user = userRepo.findByEmail(userEmail).orElseThrow(RuntimeException::new);
        Account account = accountRepo.findByIdAccount(user.getAccount().getIdAccount()).orElseThrow(RuntimeException::new);
        account.setBalance(account.getBalance()-tariff.getPrice());
        user.getTariffs().add(tariff);
        user = userRepo.save(user);
        return tariff;
    }
//    public ServiceSubscribingAcknowledgment subscribingCheck(ServiceSubscribingRequest request) {
//        ServiceSubscribingAcknowledgment acknowledgment = null;
//        Account account = request.getAccount();
//        account = accountRepo.save(account);
//
//        Tariff tariff=request.getTariff();
//
//
//    }
}
