package springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.Account;
import springboot.repository.AccountRepo;
import springboot.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public void addAccount(Account account) {
        account.setBalance(0.0);
        this.accountRepo.save(account);
    }
}
