package springboot.utils;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {
    private static Map<String, Double> paymentMap = new HashMap<>();

    {
        paymentMap.put("acc1", 120.0);
        paymentMap.put("acc2", 100.0);
        paymentMap.put("acc3", 50.0);
        paymentMap.put("acc4", 80.0);
    }

    public static boolean validateCreditLimit(String accNo, double paidAmount) {
        if (paidAmount > paymentMap.get(accNo)) {
            throw new RuntimeException("insufficient fund exception");
        } else return true;
    }
}
