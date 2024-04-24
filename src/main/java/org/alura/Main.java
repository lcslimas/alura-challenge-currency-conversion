package org.alura;

import org.alura.model.ConversionRatesOptions;
import org.alura.model.ExchangeRateResult;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String API_KEY = "7238eb5a18c7e6ba98a571b1";

    public static void main(String[] args) {
        String API = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(ExchangeRateResult::new)
                .thenAccept(exchangeResult -> {
                    Map<String, Double> conversionRates = exchangeResult.getConversion_rates();

                    new Main().printOptions();
                    Scanner scanner = new Scanner(System.in);
                    int option = scanner.nextInt();
                    while (option != 7) {
                        if(option > 7 || option < 1) {
                            System.out.println("Opção inválida");
                            new Main().printOptions();
                            option = scanner.nextInt();
                        }
                        System.out.println("Digite o valor que deseja converter:");
                        double value = scanner.nextDouble();
                        String currencies = ConversionRatesOptions.getByValue(option).toString();
                        String[] currenciesArray = currencies.split("_");

                        double result = Arrays.stream(currencies.split("_"))
                                .map(conversionRates::get)
                                .reduce((a, b) -> b / a).orElse(0.0) * value;

                        System.out.println("Valor: " + value + " [" + currenciesArray[0] + "] " + "corresponde ao valor final de =>>> " + result + " [" + currenciesArray[1] + "]");
                        System.out.println("********************************************************");

                        new Main().printOptions();
                        option = scanner.nextInt();
                    }
                    scanner.close();
                })
                .join();
    }

    private void printOptions() {
        System.out.println("Seja bem-vindo ao Conversor de Moeda =]");
        System.out.println();
        System.out.println("1) Dólar >>> Peso Argentino");
        System.out.println("2) Peso Argentino >>> Dólar");
        System.out.println("3) Dólar >>> Real Brasileiro");
        System.out.println("4) Real Brasileiro >>> Dólar");
        System.out.println("5) Dólar >>> Peso Colombiano");
        System.out.println("6) Peso Colombiano >>> Dólar");
    }
}