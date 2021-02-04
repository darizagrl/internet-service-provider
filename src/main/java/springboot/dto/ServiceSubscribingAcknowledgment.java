package springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.entity.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceSubscribingAcknowledgment {
    private String status;
    private double totalFare;
    private Account account;
}
