package springboot.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account_id")
    @JoinTable(name = "users",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "account_id"))
    private Integer idAccount;

    @Column(name = "account")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long account;

    @Column(name = "balance")
    @Min(0)
    private Double balance;

    public Account(Long account, Double balance) {
        this.account = account;
        this.balance = balance;
    }

    public Account() {
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", account=" + account +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account1 = (Account) o;
        return Objects.equals(idAccount, account1.idAccount) &&
                Objects.equals(account, account1.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccount, account);
    }
}
