package domain.model.bill;

import core.BillNotfoundException;

import java.util.List;

public interface BillingRepository {
    List<Billing> getAllBillByWalletId(Long walletId);
    Billing getById(String billId) throws BillNotfoundException;
    void save(Billing billing);
}
