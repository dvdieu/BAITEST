package port.adapter.repository;


import domain.model.wallet.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class WalletRepository implements domain.model.wallet.WalletRepository {
    HashMap<Long, Wallet> inmemoryDB;

    public WalletRepository() {
        this.inmemoryDB = new HashMap<>();
        this.inmemoryDB.put(1L, new Wallet(1, BigDecimal.ZERO));
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
    public void save(Wallet wallet) {
        this.inmemoryDB.put(wallet.getId(),wallet);
    }
}
