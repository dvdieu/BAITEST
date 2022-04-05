import core.BillNotfoundException;
import core.BillingHasBeenPaidException;
import core.WalletPaymentException;
import domain.model.bill.EPROVIDER;
import domain.model.mservices.MServices;
import domain.model.mservices.MServicesImpl;
import domain.model.wallet.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        MServices mServices = MServicesImpl.getInstance();
        String command = args.length > 0 ? args[0] : "";
        List<String> values = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            values.add(args[i]);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLEASE WAIT LOAD DATABASE");
        Wallet wallet = mServices.getWallet(1L);
        do {
            if (wallet == null) {
                System.out.println("Wallet Not Found, Please Input: Y for auto create wallet\n");
                String rs = scanner.nextLine();
                if (rs.equalsIgnoreCase("Y")) {
                    Wallet walletNew = mServices.initWallet(1L, BigDecimal.valueOf(100000000));
                    System.out.println("Wallet INIT");
                    System.out.println("Wallet Info");
                    System.out.println(walletNew.toString());
                    break;
                }
            } else {
                System.out.println("Wallet INIT");
                System.out.println("Wallet Info");
                System.out.println(wallet.toString());
            }
        } while (mServices.getWallet(1L) == null);
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
                        System.out.println("Your available balance: " + mServices.getWallet(wallet.getId()).getBalance());
                    }
                } catch (BillingHasBeenPaidException exception) {
                    System.out.println(String.format("Thanks ! a bill %s has been paid and error %s", exception.getBillID(), exception.getMessage()));
                } catch (BillNotfoundException e) {
                    System.out.println("Sorry! Not found a bill with such id");
                } catch (WalletPaymentException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "SEARCH_BILL_BY_PROVIDER": {
                System.out.println("Bill No.\tType \t Amount\t Due Date\t State\n");
                mServices.searchBill(EPROVIDER.valueOf(args[1])).forEach(billing -> {
                    System.out.println(String.format("%s\t%s\t%s\t%s\t%s", billing.getId(), billing.getType(), billing.getAmount(), billing.getDueDate(), billing.getState()));
                });
                break;
            }

            case "LIST_PAYMENT": {
                System.out.println("No.\t Amount\t Payment Date\t State\tBillId\n");
                mServices.searchPayment().forEach(payment -> {
                    System.out.println(String.format("%s\t%s\t%s\t%s\t%s", payment.getId(), payment.getAmount(), payment.getPaymentDate(), payment.getPaymentState(), payment.getBillingId()));
                });
                break;
            }
            case "INIT_TEST": {
                mServices.createTest();
                break;
            }
        }
    }
}
