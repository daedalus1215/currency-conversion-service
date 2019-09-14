package com.microservices.currencyconversionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CunrrencyConverterController {

    @GetMapping("/convert-currency/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionValueObject convertCurrency(@PathVariable String from,
                                                         @PathVariable String to,
                                                         @PathVariable BigDecimal quantity) {
        return new CurrencyConversionValueObject(from, to, BigDecimal.ONE, BigDecimal.ONE, quantity, 0);
    }
}
