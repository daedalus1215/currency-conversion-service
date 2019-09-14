package com.microservices.currencyconversionservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CunrrencyConverterController {

    @GetMapping("/convert-currency/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionValueObject convertCurrency(@PathVariable String from,
                                                         @PathVariable String to,
                                                         @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        final ResponseEntity<CurrencyConversionValueObject> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionValueObject.class,
                uriVariables);

        final CurrencyConversionValueObject response = responseEntity.getBody();

        return new CurrencyConversionValueObject(
                response.getFrom(),
                response.getTo(),
                response.getConversionMultiple(),
                response.getQuantity(),
                quantity.multiply(response.getConversionMultiple()),
                response.getPort());
    }
}
