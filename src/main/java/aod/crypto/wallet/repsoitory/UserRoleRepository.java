package aod.crypto.wallet.repsoitory;


import aod.crypto.wallet.entity.Role;
import aod.crypto.wallet.entity.UserRole;
import aod.crypto.wallet.entity.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRoleName(UserRole userRole);

}
