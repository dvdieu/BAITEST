import core.BillNotfoundException;
import core.WalletPaymentException;
import domain.model.mservices.MServices;
import domain.model.mservices.MServicesImpl;
import port.adapter.repository.BillingRepository;
import port.adapter.repository.WalletRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        MServices mServices = MServicesImpl.getInstance(new WalletRepository(), new BillingRepository());

        String command = args[0];
        List<String> values = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            values.add(args[i]);
        }

        switch (command) {
            case "CASH_IN": {
                if (values.get(0) == null) {
                    System.out.println("COMMAND NOT FOUND");
                    return;
                }
                try {
                    System.out.println("Your available balance: " + mServices.cashId(1L, BigDecimal.valueOf(Long.parseLong(values.get(0)))));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "PAY": {
                try {
                    for (int i = 0; i < values.size(); i++) {
                        mServices.pay(values.get(i));
                    }
                } catch (BillNotfoundException e) {
                    System.out.println(e.getMessage());
                } catch (WalletPaymentException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "LIST_PAYMENT": {
                break;
            }
        }
    }
}
