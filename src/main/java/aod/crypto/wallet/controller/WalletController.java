package aod.crypto.wallet.controller;

import aod.crypto.wallet.dto.TransferDTO;
import aod.crypto.wallet.service.WalletService;
import aod.crypto.wallet.utils.TOTPUtils;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.OperationsException;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/wallet")
public class WalletController {

    private final String generateSecretKey;
    private final WalletService walletService;

    @GetMapping(value = "/qr", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getQR() throws IOException, WriterException {
        return TOTPUtils.createQRCode(TOTPUtils.getGoogleAuthenticatorBarCode(generateSecretKey, "norayrgh@gmail.com", "Wallet"), 500, 500);

    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<String> transferTo(Principal principal, @RequestBody TransferDTO transferDTO) {
        try {
            return ResponseEntity.ok(walletService.transfer(principal.getName(), transferDTO.getToUsername(), transferDTO.getAmount()));
        } catch (OperationsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
