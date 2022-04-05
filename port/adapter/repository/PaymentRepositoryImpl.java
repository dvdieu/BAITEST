package port.adapter.repository;

import domain.model.mservices.Payment;
import domain.model.mservices.PaymentRepository;
import port.adapter.repository.file.DBFileStored;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepositoryImpl implements PaymentRepository {
    HashMap<String, Payment> inmemoryDB;
    String dbName = "payment.dat";
    DBFileStored<Payment> fileStored;

    public PaymentRepositoryImpl() throws IOException, ClassNotFoundException {
        this.inmemoryDB = new HashMap<>();
        this.fileStored = new DBFileStored<Payment>();
        loadDB();
    }

    private void loadDB() throws IOException, ClassNotFoundException {
        fileStored.load(dbName).forEach(payment -> {
            this.inmemoryDB.put(payment.getId(), payment);
        });
    }

    @Override
    public Payment getPayment(String paymentId) {
        return null;
    }

    @Override
    public List<Payment> listPayment() {
        return this.inmemoryDB.values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(Payment payment) throws IOException {
        this.inmemoryDB.put(payment.getId(), payment);
        this.fileStored.save(new ArrayList<>(inmemoryDB.values()), dbName);
    }
}
