package com.app.wallet.Repository;
import com.app.wallet.Entity.wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<wallet, String> {
    wallet findByGovId(String govId);
}
