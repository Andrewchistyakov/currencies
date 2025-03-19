package ru.tbank.fdsspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.model.CurrencyRequest;
import ru.tbank.fdsspring.service.CurrencyService;

import java.util.UUID;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<?> getCurrencies() {
        return new ResponseEntity<>(currencyService.getCurrencies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postCurrencies(@RequestBody CurrencyRequest currencyRequest) {
        Currency currency = new Currency();
        currency.setId(UUID.randomUUID().toString());
        currency.setName(currencyRequest.getName());
        currency.setBaseCurrency(currencyRequest.getBaseCurrency());
        currency.setPriceChangeRate(currencyRequest.getPriceChangeRange());
        currency.setDescription(currencyRequest.getDescription());

        if (currencyService.addCurrency(currency)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrenciesById(@PathVariable String id) {
        Currency currency = currencyService.getCurrencies().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (currency == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(currency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> postCurrenciesById(@PathVariable String id,
                                                @RequestBody CurrencyRequest currencyRequest) {
        Currency currency = currencyService.getCurrencies().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (currency == null) {
            return ResponseEntity.notFound().build();
        }

        currency.setName(currencyRequest.getName());
        currency.setBaseCurrency(currencyRequest.getBaseCurrency());
        currency.setPriceChangeRate(currencyRequest.getPriceChangeRange());
        currency.setDescription(currencyRequest.getDescription());

        if (!currencyService.deleteCurrency(id)) {
            return ResponseEntity.badRequest().build();
        };

        if (currencyService.addCurrency(currency)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrenciesById(@PathVariable String id) {
        boolean isDeleted = currencyService.deleteCurrency(id);
        if (isDeleted) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
