package com.app.transaction.Proxies;


import com.app.transaction.Dto.WalletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "WALLET", url = "http://localhost:8081")
public interface WalletProxy {
    @GetMapping("/api/v1/wallet/get/{govId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable  String govId);
}
