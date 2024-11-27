package com.ch.cryptotracker.service;

import com.ch.cryptotracker.model.Crypto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoService {

    private static final String BASE_URL = "https://api.coingecko.com/api/v3/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Crypto getCryptoData(String cryptoName) {
        String url = BASE_URL + "coins/markets?vs_currency=usd&ids=" + cryptoName;
        return restTemplate.getForObject(url, Crypto[].class)[0];
    }

    public Crypto getCryptoPrice(String cryptoId) {
        String url = BASE_URL + "coins/markets?vs_currency=usd&ids=" + cryptoId;
        return restTemplate.getForObject(url, Crypto[].class)[0];
    }

    public String getPricePrediction(String cryptoId) {
        return "Predicted price: $ " + (Math.random() * 100);
    }

    public String getPriceChange(String cryptoId) {
        Crypto crypto = getCryptoPrice(cryptoId);
        return crypto.getPriceChange24h() > 0 ? "Gain" : "Loss";
    }

    public Object getHistoricalData(String cryptoId) {
        String url = BASE_URL + "coins/" + cryptoId + "/market_chart?vs_currency=usd&days=7";
        return restTemplate.getForObject(url, Object.class);
    }
}
