package aod.crypto.wallet.service;

import aod.crypto.wallet.dto.WalletUserSignUpDTO;
import aod.crypto.wallet.entity.Wallet;
import aod.crypto.wallet.entity.WalletUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class UserSignUpServiceImpl implements UserSignUpService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;

    @Override
    public WalletUser signup(WalletUserSignUpDTO walletUserSignUpDTO) {
        walletUserSignUpDTO.setPassword(passwordEncoder.encode(walletUserSignUpDTO.getPassword()));
        var walletUser = userService.save(walletUserSignUpDTO);
        var etherWallet = walletService.createEtherWallet(walletUser.getUuid(), walletUser.getPassword());
        return etherWallet.getUser();
    }
}
