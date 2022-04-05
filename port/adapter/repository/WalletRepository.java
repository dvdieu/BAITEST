package port.adapter.repository;


import domain.model.wallet.Wallet;
import port.adapter.repository.file.DBFileStored;

import java.io.IOException;
import java.util.*;

public class WalletRepository implements domain.model.wallet.WalletRepository {
    final DBFileStored<Wallet> dbFileStored;
    HashMap<Long, Wallet> inmemoryDB;
    String dbName = "walletdb.dat";

    public WalletRepository() throws IOException, ClassNotFoundException {
        this.inmemoryDB = new HashMap<>();
        dbFileStored = new DBFileStored<Wallet>();
        System.out.println("LOADING DATABASE");
        dbFileStored.load(dbName).forEach(o -> {
            inmemoryDB.put(o.getId(), o);
        });
    }

    @Override
    public List<Wallet> getAllWallet() {
        return (List<Wallet>) this.inmemoryDB.values();
    }

    @Override
    public Optional<Wallet> findWalletById(Long id) {
        if (this.inmemoryDB.containsKey(id)) {
            return Optional.ofNullable(this.inmemoryDB.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void save(Wallet wallet) throws IOException {
        this.inmemoryDB.put(wallet.getId(), wallet);
        this.dbFileStored.save(new ArrayList<>(inmemoryDB.values()), "walletdb.dat");
    }
}
