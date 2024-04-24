package org.alura.model;

import com.google.gson.Gson;

import java.util.Map;

public class ExchangeRateResult {
    private final Map<String, Double> conversion_rates;

    public ExchangeRateResult(String result) {
        Gson gson = new Gson();
        ExchangeRateResult exchangeRateResult = gson.fromJson(result, ExchangeRateResult.class);
        this.conversion_rates = exchangeRateResult.getConversion_rates();
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }
}
