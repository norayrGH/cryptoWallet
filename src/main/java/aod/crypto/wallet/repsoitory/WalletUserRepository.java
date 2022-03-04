package aod.crypto.wallet.repsoitory;


import aod.crypto.wallet.entity.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletUserRepository extends JpaRepository<WalletUser, Long> {

    Optional<WalletUser> findWalletUserByEmail(String username);
    Optional<WalletUser> findWalletUserByUuid(UUID uuid);

}
