package ru.tbank.fdsspring.model;

import lombok.Data;

@Data
public class CurrencyRequest {
    private String name;
    private String baseCurrency;
    private String priceChangeRange;
    private String description;
}
