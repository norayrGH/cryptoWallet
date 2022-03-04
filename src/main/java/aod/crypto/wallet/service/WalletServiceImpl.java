package aod.crypto.wallet.service;

import aod.crypto.wallet.entity.Wallet;
import aod.crypto.wallet.entity.WalletUser;
import aod.crypto.wallet.repsoitory.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import javax.management.OperationsException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;

import static aod.crypto.wallet.entity.CoinType.ETH;

@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserService userService;
    private final WalletRepository walletRepository;
    private final Web3j web3j;

    @Override
    public Wallet createEtherWallet(final UUID userUUID, final String password) {

        var walletUser = userService.findByUuid(userUUID)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with this uuid: %s doesn't exist", userUUID))
                );

        log.info("Starting create");
        Map<String, Object> map = createCredentials(password);
        var jsonFile = map.get("jsonFile");
        var credentials = (Credentials) map.get("credentials");
        var passwordKey = (String) map.get("passwordKey");
        var address = credentials.getAddress();
        var wallet = Wallet.of(walletUser, address, jsonFile.toString(), passwordKey, ETH);
        return walletRepository.save(wallet);
    }

    @Override
    public String transfer(String fromUsername, final String toUsername, final BigDecimal amount) throws OperationsException {


        WalletUser fromUser = userService.findByEmail(fromUsername).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with this username: %s doesn't exist", fromUsername))
        );
        WalletUser toUser = userService.findByEmail(toUsername).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with this uuid: %s doesn't exist", toUsername))
        );
        Wallet fromWallet = walletRepository.findWalletsByUser(fromUser).get(0);
        Wallet toWallet = walletRepository.findWalletsByUser(toUser).get(0);
        try {
            Credentials credentials = WalletUtils.loadCredentials(fromWallet.getPassword(), fromWallet.getFileName());
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                    web3j, credentials, toWallet.getAddress(), amount, Convert.Unit.ETHER
            ).send();
            return transactionReceipt.getTransactionHash();
        } catch (Exception e) {
            log.error("Transaction failed {}", e.getMessage());
            throw new OperationsException(e.getMessage());
        }
    }

    private Map<String, Object> createCredentials(String password) {
        Map<String, Object> map = new HashMap<>();
        try {
            var walletFile = new File("src/resources/generated/");
            var fileName = WalletUtils.generateFullNewWalletFile(password, walletFile);

            log.info("wallet file name {} ", fileName);
            File jsonFile = new File(walletFile + File.separator + fileName);
            log.debug("wallet file jsonFile: {}", jsonFile);
            Credentials credentials = WalletUtils.loadCredentials(password, jsonFile);
            log.debug("wallet address: {}", credentials.getAddress());

            map.put("jsonFile", jsonFile);
            map.put("credentials", credentials);
            map.put("passwordKey", password);
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException
                | CipherException e) {
            log.error("Wallet creation failed {}", e.getMessage());

        }
        return map;
    }
}
