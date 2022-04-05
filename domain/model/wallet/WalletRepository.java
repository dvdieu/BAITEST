package domain.model.wallet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    List<Wallet> getAllWallet();

    Optional<Wallet> findWalletById(Long id);
    void save(Wallet wallet) throws IOException;
}
