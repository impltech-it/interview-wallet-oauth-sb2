package com.impltech.testoauth.domain;

import com.impltech.testoauth.enumeration.Currency;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
@Data
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="users_id")
    private User user;

    public Wallet(Currency currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Wallet() {
    }
}
