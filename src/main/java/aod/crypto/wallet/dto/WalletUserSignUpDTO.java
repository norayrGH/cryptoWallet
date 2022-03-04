package aod.crypto.wallet.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletUserSignUpDTO {

    private String email;
    private String password;
    private String name;
}
