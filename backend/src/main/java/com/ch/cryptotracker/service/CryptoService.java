package com.ch.cryptotracker.service;

import com.ch.cryptotracker.model.CandlestickDataEntity;
import com.ch.cryptotracker.repository.CandlestickDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CryptoService {

    private static final String BinanceApi = "https://api.binance.com";

    private final CandlestickDataRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    public CryptoService(CandlestickDataRepository repository) {
        this.repository = repository;
    }

    public List<CandlestickDataEntity> findBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }

    public List<CandlestickDataEntity> saveAndGetData(String symbol, String interval, String startTime, String endTime) {
        saveCandlestickData(symbol, interval, startTime, endTime);
        return findBySymbol(symbol);
    }

    public void saveCandlestickData(String symbol, String interval, String startTuime, String endTime) {
        String url = BinanceApi + "/api/v3/klines?symbol=" + symbol + "&interval=" + interval + "&startTime=" + startTuime + "&endTime=" + endTime;

        Object[][] rawData = restTemplate.getForObject(url, Object[][].class);

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
}
