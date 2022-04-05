package domain.model.mservices;

import java.io.IOException;
import java.util.List;

public interface PaymentRepository {
    Payment getPayment(String paymentId);

    List<Payment> listPayment();

    void save(Payment payment) throws IOException;
}
