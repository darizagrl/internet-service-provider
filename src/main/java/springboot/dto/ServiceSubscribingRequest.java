package springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.entity.Account;
import springboot.entity.Tariff;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceSubscribingRequest {
    private Account account;
    private Tariff tariff;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
