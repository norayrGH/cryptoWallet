package aod.crypto.wallet.service;



import aod.crypto.wallet.dto.WalletUserSignUpDTO;
import aod.crypto.wallet.entity.WalletUser;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<WalletUser> findByEmail(String username);

    WalletUser save(WalletUserSignUpDTO user);

    Optional<WalletUser> findByUuid(UUID userUuid);
}
