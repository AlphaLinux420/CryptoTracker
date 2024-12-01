package com.ch.cryptotracker.controller;

import com.ch.cryptotracker.model.CandlestickDataEntity;
import com.ch.cryptotracker.service.CryptoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
@CrossOrigin("*")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/cryptoData/{symbol}")
    public List<CandlestickDataEntity> getKlineData(@PathVariable String symbol) {
        return cryptoService.findBySymbol(symbol);
    }

    @PostMapping("/cryptoData/save")
    public void saveCandlestickData(@RequestParam String symbol, @RequestParam String interval, @RequestParam String startTime, @RequestParam String endTime) {
        cryptoService.saveCandlestickData(symbol, interval, startTime, endTime);
    }

    @GetMapping("/cryptoData/get")
    public List<CandlestickDataEntity> saveAndGetData(@RequestParam String symbol, @RequestParam
    String interval, @RequestParam String startTime, @RequestParam String endTime) {
        return cryptoService.saveAndGetData(symbol, interval, startTime, endTime);
    }
}
