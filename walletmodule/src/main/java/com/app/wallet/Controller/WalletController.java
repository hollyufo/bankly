package com.app.wallet.Controller;

import com.app.wallet.Dto.walletDto;
import com.app.wallet.Entity.wallet;
import com.app.wallet.Service.WalletService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    // create wallet
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public wallet createWallet(@RequestBody walletDto walletDto) {
        return walletService.createWallet(walletDto.getGovId(), walletDto.getBalance());
    }
    // get wallet
    @GetMapping("/get/{govId}")
    public wallet getWallet(@PathVariable String govId) {
        return walletService.getWallet(govId);
    }
    // update wallet balance
    @PutMapping("/update/{govId}/{balance}")
    public wallet updateWallet(@PathVariable String govId, @PathVariable double balance) {
        return walletService.updateWallet(govId, balance);
    }
}
