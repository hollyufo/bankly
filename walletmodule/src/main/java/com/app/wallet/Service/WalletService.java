package com.app.wallet.Service;
import com.app.wallet.Exceptions.WalletNotFoundException;
import com.app.wallet.Repository.WalletRepository;
import com.app.wallet.Entity.wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public wallet getWallet(String govId) {
        wallet wallet = walletRepository.findByGovId(govId);
        if (wallet == null) {
            throw new WalletNotFoundException(String.format("Wallet with govId %s not found", govId));
        }
        return wallet;
    }

    public wallet createWallet(String govId, double balance) {
        if (govId == null) {
            throw new IllegalArgumentException("govId cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must be a positive number");
        }
        wallet newWallet = wallet.builder()
                .govId(govId)
                .balance(balance)
                .build();
        return walletRepository.save(newWallet);
    }

    public wallet updateWallet(String govId, double balance) {
        if (govId == null) {
            throw new IllegalArgumentException("govId cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must be a positive number");
        }
        wallet wallet = walletRepository.findByGovId(govId);
        if (wallet == null) {
            throw new WalletNotFoundException(String.format("Wallet with govId %s not found", govId));
        }
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }
}
