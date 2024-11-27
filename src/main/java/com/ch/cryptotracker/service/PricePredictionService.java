package com.ch.cryptotracker.service;

import com.ch.cryptotracker.model.Crypto;
import org.springframework.stereotype.Service;

@Service
public class PricePredictionService {

    public String getPrediction(Crypto cryptoData) {
        double priceChange24h = cryptoData.getPriceChange24h();

        if (priceChange24h > 0) {
            return "Buy";
        } else if (priceChange24h < 0) {
            return "Sell";
        } else {
            return "Hold";
        }
    }
}
