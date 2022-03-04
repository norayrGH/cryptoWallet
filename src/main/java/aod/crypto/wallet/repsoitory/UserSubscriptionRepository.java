package aod.crypto.wallet.repsoitory;


import aod.crypto.wallet.entity.Role;
import aod.crypto.wallet.entity.Subscription;
import aod.crypto.wallet.entity.UserRole;
import aod.crypto.wallet.entity.UsersSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findSubscriptionBySubscriptionType(UsersSubscription subscriptionType);
}
