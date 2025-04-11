package ru.tbank.fdsspring.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.model.CurrencyRequest;
import ru.tbank.fdsspring.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CurrencyService {

    final private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        try {
            return currencyRepository.findAllCurrencies().toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean addCurrency(CurrencyRequest currencyRequest) {
        Currency currency = new Currency();
        currency.setName(currencyRequest.getName());
        currency.setBaseCurrency(currencyRequest.getBaseCurrency());
        currency.setPriceChangeRate(currencyRequest.getPriceChangeRange());
        currency.setDescription(currencyRequest.getDescription());

        try {
            currencyRepository.save(currency);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteCurrency(Long id) {
        try {
            currencyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Currency getCurrency(Long id) {

        return currencyRepository.findById(id).isPresent() ? currencyRepository.findById(id).get() : null;
    }

    public boolean updateCurrency(CurrencyRequest currencyRequest, Long id) {

        Currency currency = currencyRepository.findById(id).isPresent() ? currencyRepository.findById(id).get() : null;
        currency.setName(currencyRequest.getName());
        currency.setBaseCurrency(currencyRequest.getBaseCurrency());
        currency.setPriceChangeRate(currencyRequest.getPriceChangeRange());
        currency.setDescription(currencyRequest.getDescription());

        try {
            currencyRepository.save(currency);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
