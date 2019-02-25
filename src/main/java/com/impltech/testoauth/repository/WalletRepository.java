package com.impltech.testoauth.repository;

import com.impltech.testoauth.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> getAllUsersWalletsByUserId(@Param("userId") Long userId);
}
