package com.impltech.testoauth.service;

import com.impltech.testoauth.domain.User;
import com.impltech.testoauth.domain.Wallet;
import com.impltech.testoauth.exception.LimitException;
import com.impltech.testoauth.repository.UserRepository;
import com.impltech.testoauth.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
@Service
public class WalletService {

    private final Logger log = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    /**
     * Replenish balance from one wallet to another wallet
     */
    @Transactional
    public boolean replenishBalance(Long fromWallet, Long toWallet, Double amount) {
        if (fromWallet != null && toWallet != null && amount != null) {
            subtract(fromWallet, amount);
            add(toWallet, amount);
            return true;
        }
        return false;
    }

    /**
     * Add balance
     */
    @Transactional
    public void add(Long userWalletId, Double amount) {
        if (amount != null && userWalletId !=null) {
            Wallet userWallet = walletRepository.getOne(userWalletId);
            addBalance(amount, userWallet);
            walletRepository.save(userWallet);
        }
    }

    private void addBalance(Double amount, Wallet userWallet) {
        Double currentAmount = userWallet.getAmount();
        currentAmount += amount;
        userWallet.setAmount(currentAmount);
    }

    /**
     * Subtract balance
     */
    @Transactional
    public void subtract(Long userWalletId, Double amount) {
        if (amount != null && userWalletId !=null) {
            Wallet userWallet = walletRepository.getOne(userWalletId);
            subtractBalance(amount, userWallet);
            walletRepository.save(userWallet);
        }
    }

    private void subtractBalance(Double amount, Wallet userWallet) {
        Double currentAmount = userWallet.getAmount();
        currentAmount -= amount;
        userWallet.setAmount(currentAmount);
    }

    /**
     * Delete the wallet by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        if (id != null) {
            log.debug("Request to delete Wallet : {}", id);
            walletRepository.deleteById(id);
        }
    }

    /**
     * Add new wallet.
     */
    @Transactional
    public Wallet addWallet(Long userId, Wallet wallet) {
        if (userId != null && wallet != null) {
            List<Wallet> userWallets = walletRepository.getAllUsersWalletsByUserId(userId);
            if (userWallets.size() < 3) {
                Wallet newWallet = walletRepository.save(wallet);
                User user = userRepository.getOne(userId);
                user.getWallets().add(newWallet);
                userRepository.save(user);
                return newWallet;
            } else {
                throw new LimitException("You can create only 3 wallets");
            }
        }
        return null;
    }

    /**
     * Get allUsersWallets by userId.
     */
    @Transactional
    public List<Wallet> getAllUserWallets(Long userId) {
        if (userId != null) {
            return walletRepository.getAllUsersWalletsByUserId(userId);
        }
        return null;
    }
}
