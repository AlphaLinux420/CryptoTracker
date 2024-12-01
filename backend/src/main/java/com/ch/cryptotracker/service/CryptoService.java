package com.ch.cryptotracker.service;

import com.ch.cryptotracker.model.CandlestickData;
import com.ch.cryptotracker.model.CandlestickDataEntity;
import com.ch.cryptotracker.model.Crypto;
import com.ch.cryptotracker.repository.CandlestickDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CryptoService {

    private static final String BASE_URL = "https://api.coingecko.com/api/v3/";

    private static final String BinanceApi = "https://api.binance.com";

    private final CandlestickDataRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    public CryptoService(CandlestickDataRepository repository) {
        this.repository = repository;
    }


    public List<CandlestickDataEntity> findBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }

    public void saveCandlestickData(String symbol, String interval, String startTuime, String endTime) {
        String url = BinanceApi + "/api/v3/klines?symbol=" + symbol + "&interval=" + interval + "&startTime=" + startTuime + "&endTime=" + endTime;

        List<Object[]> rawData = Arrays.asList(restTemplate.getForObject(url, Object[][].class));

        for (Object[] data : rawData) {
            CandlestickDataEntity candlestickDataEntity = new CandlestickDataEntity();
            candlestickDataEntity.setSymbol(symbol);
            candlestickDataEntity.setOpen(Double.parseDouble(data[1].toString()));
            candlestickDataEntity.setHigh(Double.parseDouble(data[2].toString()));
            candlestickDataEntity.setLow(Double.parseDouble(data[3].toString()));
            candlestickDataEntity.setClose(Double.parseDouble(data[4].toString()));
            candlestickDataEntity.setVolume(Double.parseDouble(data[5].toString()));
            candlestickDataEntity.setOpentime(String.valueOf(data[0]));
            candlestickDataEntity.setClosetime(String.valueOf(data[6]));

            repository.save(candlestickDataEntity);
        }
    }


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
