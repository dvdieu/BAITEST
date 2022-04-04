package domain.model.mservices;

import core.BillNotfoundException;
import core.WalletPaymentException;
import core.WalletTopUpException;
import domain.model.bill.Billing;
import domain.model.bill.BillingRepository;
import domain.model.bill.EState;
import domain.model.wallet.Wallet;
import domain.model.wallet.WalletRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class MServicesImpl implements MServices {
    private volatile static MServices _instance;

    public static MServices getInstance(WalletRepository walletRepository, BillingRepository billingRepository) {
        if (_instance == null) {
            synchronized (MServices.class) {
                if (_instance == null) {
                    _instance = new MServicesImpl(walletRepository, billingRepository);
                }
            }
        }
        return _instance;
    }

    WalletRepository walletRepository;

    public MServicesImpl(WalletRepository walletRepository, BillingRepository billingRepository) {
        this.walletRepository = walletRepository;
        this.billingRepository = billingRepository;
    }

    BillingRepository billingRepository;


    @Override
    public BigDecimal cashId(Long id, BigDecimal amount) throws WalletTopUpException {
        Optional<Wallet> wallet = walletRepository.findWalletById(id);
        if (wallet.isPresent()) {
            wallet.get().cashIn(amount);
            walletRepository.save(wallet.get());
            return wallet.get().getBalance();
        }
        throw new WalletTopUpException("Topup fail");
    }

    @Override
    public Wallet getWallet(Long id) throws Exception {
        Optional<Wallet> wallet = walletRepository.findWalletById(id);
        if (wallet.isPresent()) {
            return wallet.get();
        }
        throw new Exception("Wallet Not Exists");
    }

    @Override
    public List<Billing> getListBill(Long walletId) {
        return billingRepository.getAllBillByWalletId(walletId);
    }


    @Override
    public BigDecimal pay(String billId) throws BillNotfoundException, WalletPaymentException {
        Billing billing = billingRepository.getById(billId);
        Wallet wallet = walletRepository.findWalletById(billing.getWalletId()).get();
        wallet.payment(billing.getAmount());
        billing.setState(EState.PROCESSED);
        walletRepository.save(wallet);
        billingRepository.save(billing);
        return wallet.getBalance();
    }
}
