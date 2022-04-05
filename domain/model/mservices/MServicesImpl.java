package domain.model.mservices;

import core.BillNotfoundException;
import core.BillingHasBeenPaidException;
import core.WalletPaymentException;
import core.WalletTopUpException;
import domain.model.application.command.NewPaymentCommand;
import domain.model.bill.Billing;
import domain.model.bill.BillingRepository;
import domain.model.bill.EPROVIDER;
import domain.model.wallet.Wallet;
import domain.model.wallet.WalletRepository;
import port.adapter.repository.PaymentRepositoryImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MServicesImpl implements MServices {
    private volatile static MServices _instance;

    public static MServices getInstance() throws IOException, ParseException, ClassNotFoundException {
        if (_instance == null) {
            synchronized (MServices.class) {
                if (_instance == null) {
                    _instance = new MServicesImpl();
                }
            }
        }
        return _instance;
    }


    PaymentServices paymentServices;
    BillingRepository billingRepository;
    WalletRepository walletRepository;
    PaymentRepository paymentRepository;

    private MServicesImpl() throws IOException, ClassNotFoundException, ParseException {
        this.billingRepository = new port.adapter.repository.BillingRepository();
        this.paymentRepository = new PaymentRepositoryImpl();
        this.walletRepository = new port.adapter.repository.WalletRepository();
        this.paymentServices = new PaymentServices(paymentRepository, billingRepository, walletRepository);
    }

    @Override
    public Wallet initWallet(Long walletID, BigDecimal amount) throws IOException {
        Wallet wallet = new Wallet(walletID, amount);
        this.walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public BigDecimal cashId(Long id, BigDecimal amount) throws WalletTopUpException, IOException {
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
        return null;
    }

    @Override
    public List<Billing> getListBill(Long walletId) {
        return billingRepository.getAllBillByWalletId(walletId);
    }


    @Override
    public void pay(String billId) {
        try {
            paymentServices.createPaymentDTO(new NewPaymentCommand(billId));
        } catch (BillNotfoundException e) {
            e.printStackTrace();
        } catch (BillingHasBeenPaidException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WalletPaymentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Billing> searchBill() {
        return billingRepository.getAllBilling();
    }

    @Override
    public List<Billing> searchBill(EPROVIDER provider) {
        return this.searchBill().stream().filter(billing -> billing.getProvider().equals(provider)).collect(Collectors.toList());
    }

    @Override
    public List<Payment> searchPayment() {
        return this.paymentRepository.listPayment();
    }

    @Override
    public void createTest() throws IOException, ParseException, ClassNotFoundException {
        billingRepository.clearTest();
    }
}
