package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.Account;
import springboot.repository.AccountRepo;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account findAccountId(Integer idAccount) {
        Optional<Account> optional = accountRepo.findByIdAccount(idAccount);
        Account account;
        if (optional.isPresent()) {
            account = optional.get();
        } else {
            throw new RuntimeException("Account not found for id :: " + idAccount);
        }
        return account;
    }

    @Override
    public void updateAccount(Integer idAccount, Account account) {
        this.accountRepo.save(account);
    }

    @Override
    public void addAccount(Account account) {
        this.accountRepo.save(account);
    }

    @Override
    public void deleteAccount(Integer idAccount) {
        this.accountRepo.deleteById(idAccount);
    }
}
