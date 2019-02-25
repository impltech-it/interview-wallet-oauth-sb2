package com.impltech.testoauth.web.rest.util;

import com.impltech.testoauth.config.EurValue;
import com.impltech.testoauth.config.UahValue;
import com.impltech.testoauth.config.UsdValue;
import com.impltech.testoauth.enumeration.Currency;
import org.springframework.stereotype.Component;

/**
 * Created by dima.
 * Creation date 15.02.19.
 */
@Component
public class CurrencyConverterUtil {

    private static EurValue eurValue;

    private static UsdValue usdValue;

    private static UahValue uahValue;

    public static Double convertFromUah(Double amount, Currency fromCurrency, Currency toCurrency) {
        Double result = 0.0;
        if (fromCurrency.getCurrency().equals("uah")) {
            if (toCurrency.getCurrency().equals("usd")) {
                result = amount * uahValue.getUsd();
            } else {
                result = amount * uahValue.getEur();
            }
        }
        return result;
    }

    public static Double convertFromUsd(Double amount, Currency fromCurrency, Currency toCurrency) {
        Double result = 0.0;
        if (fromCurrency.getCurrency().equals("usd")) {
            if (toCurrency.getCurrency().equals("eur")) {
                result = amount * usdValue.getEur();
            } else {
                result = amount * usdValue.getUah();
            }
        }
        return result;
    }

    public static Double convertFromEur(Double amount, Currency fromCurrency, Currency toCurrency) {
        Double result = 0.0;
        if (fromCurrency.getCurrency().equals("eur")) {
            if (toCurrency.getCurrency().equals("usd")) {
                result = amount * eurValue.getUsd();
            } else {
                result = amount * eurValue.getUah();
            }
        }
        return result;
    }
}
