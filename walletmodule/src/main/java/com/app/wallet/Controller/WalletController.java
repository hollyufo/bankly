package com.app.wallet.Controller;

import com.app.wallet.Dto.Response.WalletResponse;
import com.app.wallet.Dto.Request.walletDto;
import com.app.wallet.Entity.wallet;
import com.app.wallet.Service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/wallet")
@RequiredArgsConstructor
@Slf4j
public class WalletController {
    private final WalletService walletService;

    /**
     * Creates a new wallet.
     *
     * @param walletDto the wallet information
     * @return the created wallet
     */
    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@Valid @RequestBody walletDto walletDto) {
        // check if wallet already existsgetGovId
        wallet wallet = walletService.getwallet2(walletDto.getGovId());
        if (wallet != null) {
            return new ResponseEntity<>(new WalletResponse(wallet, "Wallet already exists"), HttpStatus.CONFLICT);
        }else{
            wallet = walletService.createWallet(walletDto.getGovId(), walletDto.getBalance());
            WalletResponse response = new WalletResponse(wallet, "Wallet created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    /**
     * Gets the wallet information for a given government ID.
     *
     * @param govId the government ID
     * @return the wallet information
     */
    @GetMapping("/get/{govId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable @NotNull String govId) {
        wallet wallet = walletService.getWallet(govId);
        if (wallet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WalletResponse response = new WalletResponse(wallet, "Wallet retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Updates the balance of a wallet.
     *
     * @param govId the government ID
     * @param balance the new balance
     * @return the updated wallet
     */
    @PutMapping("/update/{govId}/{balance}")
    public ResponseEntity<WalletResponse> updateWallet(@PathVariable @NotNull String govId,
                                                       @PathVariable @NotNull double balance) {
        wallet wallet = walletService.updateWallet(govId, balance);
        if (wallet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WalletResponse response = new WalletResponse(wallet, "Wallet balance updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}