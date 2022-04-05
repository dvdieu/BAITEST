package domain.model.mservices;

import core.BillNotfoundException;
import core.BillingHasBeenPaidException;
import core.WalletPaymentException;
import domain.model.bill.Billing;
import domain.model.bill.EPROVIDER;
import domain.model.wallet.Wallet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public interface MServices {
    Wallet initWallet(Long id, BigDecimal amount) throws IOException;

    BigDecimal cashId(Long id, BigDecimal amount) throws Exception;

    Wallet getWallet(Long id) throws Exception;

    List<Billing> getListBill(Long walletId);

    void pay(String billId) throws BillNotfoundException, WalletPaymentException, IOException, ClassNotFoundException, BillingHasBeenPaidException;

    List<Billing> searchBill();

    List<Billing> searchBill(EPROVIDER provider);

    List<Payment> searchPayment();

    void createTest() throws IOException, ParseException, ClassNotFoundException;
}
