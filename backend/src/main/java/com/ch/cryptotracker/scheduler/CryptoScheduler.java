package com.ch.cryptotracker.scheduler;

import com.ch.cryptotracker.service.CryptoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CryptoScheduler {

    private CryptoService cryptoService;

    public CryptoScheduler(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void getCryptoData() {
        System.out.println("you stupid wigga");
    }
}
