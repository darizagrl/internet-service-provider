package springboot.service;

import springboot.entity.Account;

public interface AccountService {
    Account findAccountId(Integer idAccount);

    void updateAccount(Integer idAccount, Account account);

    void addAccount(Account account);

    void deleteAccount(Integer idAccount);
}
