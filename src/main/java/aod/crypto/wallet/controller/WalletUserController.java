package aod.crypto.wallet.controller;

import aod.crypto.wallet.dto.WalletUserDTO;
import aod.crypto.wallet.dto.WalletUserSignUpDTO;
import aod.crypto.wallet.mapper.WalletMapper;
import aod.crypto.wallet.service.UserService;
import aod.crypto.wallet.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/users" )
public class WalletUserController {
    private final UserSignUpService userSignUpService;

    @PostMapping(value = "/signup" )
    public ResponseEntity<WalletUserDTO> getWalletInfo(@RequestBody WalletUserSignUpDTO walletUserSignUpDTO){
        return ResponseEntity.ok(WalletMapper.mapToWalletInfoDTO(userSignUpService.signup(walletUserSignUpDTO)));
    }
}
