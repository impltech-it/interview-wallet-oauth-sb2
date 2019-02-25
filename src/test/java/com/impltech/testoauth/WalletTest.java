package com.impltech.testoauth;

import com.impltech.testoauth.domain.Wallet;
import com.impltech.testoauth.enumeration.Currency;
import com.impltech.testoauth.repository.WalletRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dima.
 * Creation date 20.02.19.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TestOauthApplication.class)
public class WalletTest {

    private static final String URL = "/api";

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletRepository walletRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @After
    public void remove() {
        walletRepository.deleteAll();
    }

    @Test
    public void addBalance() throws Exception {
        Wallet wallet = walletRepository.save(new Wallet(Currency.EUR, 100D));
        mockMvc.perform(put(URL + "/{id}/amount/add?amount=30", wallet.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void reduceBalance() throws Exception {
        Wallet wallet = walletRepository.save(new Wallet(Currency.UAH, 100D));
        mockMvc.perform(put(URL + "/{id}/amount/reduce?amount=30", wallet.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void replenishBalance() throws Exception {
        Wallet from = walletRepository.save(new Wallet(Currency.UAH, 100D));
        Wallet to = walletRepository.save(new Wallet(Currency.UAH, 70D));
        mockMvc.perform(put(URL + "/{fromId}/replenish/{toId}?amount=50",
                from.getId(), to.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteWallet() throws Exception {
        Wallet wallet = walletRepository.save(new Wallet(Currency.UAH, 200D));
        mockMvc.perform(delete(URL + "/wallet/{id}", wallet.getId()))
                .andExpect(status().isOk());
    }
}
