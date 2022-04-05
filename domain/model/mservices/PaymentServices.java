package domain.model.mservices;

import core.BillNotfoundException;
import core.BillingHasBeenPaidException;
import core.WalletPaymentException;
import domain.model.application.command.NewPaymentCommand;
import domain.model.application.presentation.PaymentDTO;
import domain.model.bill.Billing;
import domain.model.bill.BillingRepository;
import domain.model.wallet.Wallet;
import domain.model.wallet.WalletRepository;

import java.io.IOException;

public class PaymentServices {
    PaymentRepository paymentRepository;
    BillingRepository billingRepository;
    WalletRepository walletRepository;

    public PaymentServices(PaymentRepository paymentRepository, BillingRepository billingRepository, WalletRepository walletRepository) {
        this.paymentRepository = paymentRepository;
        this.billingRepository = billingRepository;
        this.walletRepository = walletRepository;
    }


    public PaymentDTO createPaymentDTO(NewPaymentCommand newPaymentCommand) throws BillNotfoundException, BillingHasBeenPaidException, IOException, WalletPaymentException {
        Billing billing = billingRepository.getById(newPaymentCommand.getBillingId());
        Wallet wallet = walletRepository.findWalletById(billing.getWalletId()).get();
        Payment payment = new Payment(String.valueOf(System.nanoTime()), newPaymentCommand.getBillingId(), billing.getAmount(), billing.getWalletId().toString(), System.currentTimeMillis());
        try {
            wallet.payment(billing.getAmount());
            billing.payment();
            payment.setPaymentState(EPaymentState.PROCESSED);
            billingRepository.save(billing);
            walletRepository.save(wallet);
        } catch (Exception e) {
            wallet.refund(billing.getAmount());
            walletRepository.save(wallet);
        } finally {
            paymentRepository.save(payment);
        }
        return PaymentDTO.from(payment);
    }
}
