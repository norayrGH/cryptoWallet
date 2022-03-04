package aod.crypto.wallet.entity;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
public class Wallet extends BaseEntity {

    @Column(nullable = false)
    private String address;

    @Column
    private String fileName;

    @Column
    private String password;

    @Column(precision = 20, scale = 8)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private CoinType coinType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private WalletUser user;


    protected Wallet() {

    }

    private Wallet(final WalletUser user, final String address, final String fileName, final String password, final CoinType coinType) {
        super();
        this.user = user;
        this.address = address;
        this.fileName = fileName;
        this.password = password;
        this.balance = BigDecimal.valueOf(100);
        this.coinType = coinType;
    }

    public static Wallet of(final WalletUser user, final String address, final String fileName, final String password, final CoinType coinType) {
        return new Wallet(user, address, fileName, password, coinType);
    }


}