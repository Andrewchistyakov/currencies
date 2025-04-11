package ru.tbank.fdsspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.model.CurrencyRequest;
import ru.tbank.fdsspring.service.CurrencyService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currencies")
public class CurrencyController {

    final private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<?> getCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PostMapping
    public ResponseEntity<?> postCurrencies(@RequestBody CurrencyRequest currencyRequest) {

        if (currencyService.addCurrency(currencyRequest)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrenciesById(@PathVariable Long id) {
        Currency currency = currencyService.getCurrency(id);

        if (currency == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(currency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> postCurrenciesById(@PathVariable Long id,
                                                @RequestBody CurrencyRequest currencyRequest) {

        if (currencyService.updateCurrency(currencyRequest, id)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrenciesById(@PathVariable Long id) {

        if (currencyService.deleteCurrency(id)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
