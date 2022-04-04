package domain.model.mservices;

import core.BillNotfoundException;
import core.WalletPaymentException;
import domain.model.bill.Billing;
import domain.model.wallet.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface MServices {
    BigDecimal cashId(Long id, BigDecimal amount) throws Exception;

    Wallet getWallet(Long id) throws Exception;

    List<Billing> getListBill(Long walletId);

    BigDecimal pay(String billId) throws BillNotfoundException, WalletPaymentException;

}
