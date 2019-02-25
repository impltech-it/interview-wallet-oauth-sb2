package com.impltech.testoauth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dima.
 * Creation date 15.02.19.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "currency.uah")
public class UahValue {

    private Double usd;
    private Double eur;
}
