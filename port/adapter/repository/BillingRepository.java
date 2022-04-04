package port.adapter.repository;


import core.BillNotfoundException;
import domain.model.bill.Billing;
import domain.model.bill.EBillType;
import domain.model.bill.EPROVIDER;
import domain.model.bill.EState;
import domain.model.wallet.Wallet;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BillingRepository implements domain.model.bill.BillingRepository {
    HashMap<String, Billing> inmemoryDB;

    public BillingRepository() throws ParseException {
        this.inmemoryDB = new HashMap<>();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        this.inmemoryDB.put("1",
                new Billing(
                        "1", EBillType.ELECTRIC, BigDecimal.valueOf(200000L),
                        new SimpleDateFormat("dd/MM/yyyy").parse(" 25/10/2020"), EPROVIDER.EVN, EState.NOT_PAID, 1L
                )
        );
        this.inmemoryDB.put("2",
                new Billing(
                        "2", EBillType.WATER, BigDecimal.valueOf(175000),
                        new SimpleDateFormat("dd/MM/yyyy").parse(" 30/10/2020"), EPROVIDER.SAVACO, EState.NOT_PAID, 1L
                )
        );
        this.inmemoryDB.put("3",
                new Billing(
                        "3", EBillType.INTERNET, BigDecimal.valueOf(800000),
                        new SimpleDateFormat("dd/MM/yyyy").parse(" 30/10/2020"), EPROVIDER.VNPT, EState.NOT_PAID, 1L
                )
        );
    }

    public void save(Billing billing) {
        this.inmemoryDB.put(billing.getId(), billing);
    }


    @Override
    public List<Billing> getAllBillByWalletId(Long walletId) {
        return this.inmemoryDB.values().stream().filter(billing -> billing.getWalletId().equals(walletId)).collect(Collectors.toList());
    }

    @Override
    public Billing getById(String billId) throws BillNotfoundException {
        if (this.inmemoryDB.containsKey(billId)) {
            return this.inmemoryDB.get(billId);
        }
        throw new BillNotfoundException("Sorry! Not found a bill with such id");
    }
}
