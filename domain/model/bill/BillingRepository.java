package domain.model.bill;

import core.BillNotfoundException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BillingRepository {
    List<Billing> getAllBillByWalletId(Long walletId);
    List<Billing> getAllBilling();
    Billing getById(String billId) throws BillNotfoundException;
    void save(Billing billing) throws IOException, ClassNotFoundException;
    void clearTest() throws IOException, ParseException, ClassNotFoundException;
}
