package aod.crypto.wallet.mapper;

import aod.crypto.wallet.dto.WalletUserDTO;
import aod.crypto.wallet.entity.WalletUser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WalletMapper {



    public static WalletUserDTO mapToWalletInfoDTO(WalletUser walletUser){
        return WalletUserDTO.builder()
                .createdAt(walletUser.getCreatedAt())
                .updatedAt(walletUser.getUpdatedAt())
                .email(walletUser.getEmail())
                .name(walletUser.getName())
                .password(walletUser.getPassword())
                .build();

    }
}

