package com.app.wallet.Service;
import com.app.wallet.Repository.WalletRepository;
import com.app.wallet.Entity.wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public wallet getWallet(String govId) {
        return walletRepository.findByGovId(govId);
    }
    public wallet createWallet(String govId, double balance) {
        wallet newWallet = wallet.builder()
                .govId(govId)
                .balance(balance)
                .build();
        return walletRepository.save(newWallet);
    }
    // update wallet balance
    public wallet updateWallet(String govId, double balance) {
        wallet wallet = walletRepository.findByGovId(govId);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }
}
