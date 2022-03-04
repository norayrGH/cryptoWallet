package aod.crypto.wallet.service;

import aod.crypto.wallet.dto.WalletUserSignUpDTO;
import aod.crypto.wallet.entity.Subscription;
import aod.crypto.wallet.entity.UserRole;
import aod.crypto.wallet.entity.UsersSubscription;
import aod.crypto.wallet.entity.WalletUser;
import aod.crypto.wallet.repsoitory.UserRoleRepository;
import aod.crypto.wallet.repsoitory.UserSubscriptionRepository;
import aod.crypto.wallet.repsoitory.WalletUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final WalletUserRepository walletUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public Optional<WalletUser> findByEmail(String username) {
        log.info("Searching user by username {}", username);
        return walletUserRepository.findWalletUserByEmail(username);
    }

    @Override
    public WalletUser save(WalletUserSignUpDTO user) {
        log.info("Saving user by username {}", user.getEmail());
        var roleByRoleName = userRoleRepository.findRoleByRoleName(UserRole.ROLE_USER);
        var subscription = userSubscriptionRepository.findSubscriptionBySubscriptionType(UsersSubscription.TRIAL);
        return walletUserRepository.save(WalletUser.of(user.getEmail(), user.getPassword(), user.getName(), roleByRoleName, subscription));
    }

    @Override
    public Optional<WalletUser> findByUuid(UUID userUuid) {
        log.info("Searching user by {}", userUuid);
        return walletUserRepository.findWalletUserByUuid(userUuid);
    }
}
