package aod.crypto.wallet.service;

import aod.crypto.wallet.entity.Wallet;

import javax.management.OperationsException;
import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

    Wallet createEtherWallet(UUID userUuid, String password);
    String transfer(String fromUsername, String toUsername, BigDecimal amount) throws OperationsException;
}
