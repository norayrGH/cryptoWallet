package aod.crypto.wallet.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class WalletInfoDTO {
    private String ownerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID uuid;

}
