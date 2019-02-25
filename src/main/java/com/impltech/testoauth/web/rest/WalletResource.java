package com.impltech.testoauth.web.rest;

import com.impltech.testoauth.domain.Wallet;
import com.impltech.testoauth.exception.LimitException;
import com.impltech.testoauth.service.WalletService;
import com.impltech.testoauth.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
@RestController
@RequestMapping("/api")
public class WalletResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private static final String ENTITY_NAME = "wallet";

    private final WalletService walletService;

    public WalletResource(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/{id}/wallet")
    public ResponseEntity<?> createWallet(@PathVariable("id") Long userId, @RequestBody Wallet wallet) throws LimitException {
        Wallet result = walletService.addWallet(userId, wallet);
        return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE  /wallet/:id : delete the "id" wallet.
     *
     * @param id the id of the wallet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wallet/{id}")
    @Timed
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        log.debug("REST request to delete wallet : {}", id);
        walletService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * PUT  /amount : Updates balance after replenish.
     */
    @PutMapping("/{fromId}/replenish/{toId}")
    public ResponseEntity<?> replenishBalance(@PathVariable("fromId") Long fromId, @RequestParam("amount") Double amount, @PathVariable("toId") Long toId) {
        return ResponseEntity.ok().body(walletService.replenishBalance(fromId, toId, amount));
    }

    /**
     * PUT  /amount : Updates balance after add.
     */
    @PutMapping("/{id}/amount/add")
    public ResponseEntity<?> addBalance(@PathVariable("id") Long id, @RequestParam("amount") Double amount) {
        walletService.add(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT  /amount : Updates balance after reduce.
     */
    @PutMapping("/{id}/amount/reduce")
    public ResponseEntity<?> reduceBalance(@PathVariable("id") Long id, @RequestParam("amount") Double amount) {
        walletService.subtract(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET  /users/wallets : get the "id" users/wallets.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the users/wallets, or with status 404 (Not Found)
     */
    @GetMapping("/{id}/wallets")
    @Timed
    public ResponseEntity<?> getUserWallets(@PathVariable("id") Long userId) {
        return ResponseEntity.ok().body(walletService.getAllUserWallets(userId));
    }
}
