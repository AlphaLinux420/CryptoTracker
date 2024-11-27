package com.ch.cryptotracker.model;

public class CryptoPredictionResponse {

    private String name;
    private String symbol;
    private double currentPrice;
    private double priceChange24h;
    private String prediction;

    public CryptoPredictionResponse(String name, String symbol, double currentPrice, double priceChange24h, String prediction) {
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.priceChange24h = priceChange24h;
        this.prediction = prediction;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(double priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
