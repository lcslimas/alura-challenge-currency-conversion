package org.alura.model;

public enum ConversionRatesOptions {
    USD_ARS(1),
    ARS_USD(2),
    USD_BRL(3),
    BRL_USD(4),
    USD_COP(5),
    COP_USD(6);

    private final int value;

    ConversionRatesOptions(int value) {
        this.value = value;
    }

    public static ConversionRatesOptions getByValue(int value) {
        for (ConversionRatesOptions conversionRate : values()) {
            if (conversionRate.value == value) {
                return conversionRate;
            }
        }
        throw new IllegalArgumentException("Número digitado inesperado " + value);
    }
}
