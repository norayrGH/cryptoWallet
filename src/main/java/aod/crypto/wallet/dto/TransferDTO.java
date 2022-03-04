package aod.crypto.wallet.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {

    private String toUsername;
    private BigDecimal amount;

}
