package aod.crypto.wallet.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
public class Role extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1567637283572978119L;

    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    protected Role() {}

}
