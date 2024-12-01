package com.ch.cryptotracker.controller;

import com.ch.cryptotracker.model.CandlestickData;
import com.ch.cryptotracker.model.CandlestickDataEntity;
import com.ch.cryptotracker.model.Crypto;
import com.ch.cryptotracker.model.CryptoPredictionResponse;
import com.ch.cryptotracker.service.CryptoService;
import com.ch.cryptotracker.service.PricePredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
@CrossOrigin("*")
public class CryptoController {

    private final CryptoService cryptoService;
    private final PricePredictionService pricePredictionService;

    public CryptoController(CryptoService cryptoService, PricePredictionService pricePredictionService) {
        this.cryptoService = cryptoService;
        this.pricePredictionService = pricePredictionService;
    }

    @GetMapping("/api/klinedata/{symbol}")
    public List<CandlestickDataEntity> getKlineData(@PathVariable String symbol) {
        return cryptoService.findBySymbol(symbol);
    }

    @PostMapping("/api/candlestick/save")
    public void saveCandlestickData(@RequestParam String symbol, @RequestParam String interval, @RequestParam String startTime, @RequestParam String endTime) {
        cryptoService.saveCandlestickData(symbol, interval, startTime, endTime);
    }

    @GetMapping("/api/crypto/search/{cryptoId}")
    public CryptoPredictionResponse getCryptoPrediction(@PathVariable String cryptoId) {
        Crypto cryptoData = cryptoService.getCryptoData(cryptoId);

        String prediction = pricePredictionService.getPrediction(cryptoData);

        return new CryptoPredictionResponse(
                cryptoData.getName(),
                cryptoData.getSymbol(),
                cryptoData.getCurrentPrice(),
                cryptoData.getPriceChange24h(),
                prediction
        );
    }

    @GetMapping("/search/{cryptoName}")
    public Object getCryptoData(@PathVariable String cryptoName) {
        return cryptoService.getCryptoData(cryptoName);
    }

    @GetMapping("/price/{cryptoId}")
    public Object getCryptoPrice(@PathVariable String cryptoId) {
        return cryptoService.getCryptoPrice(cryptoId);
    }

    @GetMapping("/price-prediction/{cryptoId}")
    public String getPricePrediction(@PathVariable String cryptoId) {
        return cryptoService.getPricePrediction(cryptoId);
    }

    @GetMapping("/price-change/{cryptoId}")
    public String getPriceChange(@PathVariable String cryptoId) {
        return cryptoService.getPriceChange(cryptoId);
    }

    @GetMapping("/historical-data/{cryptoId}")
    public Object getHistoricalData(@PathVariable String cryptoId) {
        return cryptoService.getHistoricalData(cryptoId);
    }
}
