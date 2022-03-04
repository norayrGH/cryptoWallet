package aod.crypto.wallet.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
public class Subscription extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1564747283512329789L;

    @Enumerated(EnumType.STRING)
    private UsersSubscription subscriptionType;
}
