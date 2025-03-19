package ru.tbank.fdsspring.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Currency {

    private String id;

    private String name;

    private String baseCurrency;

    private String priceChangeRate;

    private String description;
}
