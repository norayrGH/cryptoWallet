package aod.crypto.wallet.repsoitory;


import aod.crypto.wallet.entity.Wallet;
import aod.crypto.wallet.entity.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findWalletsByUser(WalletUser walletUser);
}
