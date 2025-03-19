package ru.tbank.fdsspring.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.tbank.fdsspring.model.Currency;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class CurrencyService {

    private List<Currency> currencies = new ArrayList<>();

    public boolean addCurrency(Currency currency) {
        currencies.add(currency);
        return true;
    }

    public boolean deleteCurrency(String id) {
        currencies.removeIf(c -> c.getId().equals(id));
        return true;
    }
}
