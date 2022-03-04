package aod.crypto.wallet.service;

import aod.crypto.wallet.dto.WalletUserSignUpDTO;
import aod.crypto.wallet.entity.WalletUser;

public interface UserSignUpService {
    WalletUser signup(WalletUserSignUpDTO walletUserSignUpDTO);
}
