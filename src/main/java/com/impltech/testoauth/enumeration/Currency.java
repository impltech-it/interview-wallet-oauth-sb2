package com.impltech.testoauth.enumeration;

import lombok.Getter;

/**
 * Created by dima.
 * Creation date 15.02.19.
 */
@Getter
public enum Currency {

    UAH("uah"),
    USD("usd"),
    EUR("eur");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }
}
