package aod.crypto.wallet.entity;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
public class WalletUser extends BaseEntity implements Serializable {

    private String email;
    private String password;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    protected WalletUser() {
    }

    private WalletUser(final String email, final String password, final String name, final Role userRole, final Subscription subscription) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = userRole;
        this.subscription = subscription;
    }


    public WalletUser(@NonNull WalletUser walletUser) {
        this.email = walletUser.getEmail();
        this.password = walletUser.getPassword();
        this.name = walletUser.getName();
        this.role = walletUser.getRole();
    }


    public static WalletUser of(final String email, final String password, final String name, final Role userRole, final Subscription subscription) {
        return new WalletUser(email, password, name, userRole, subscription);
    }
}
