package com.ch.cryptotracker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public  class CandlestickData {

    private long klineOpenTime;

    private String openPrice;

    private String highPrice;

    private String lowPrice;

    private String closePrice;

    private String volumePrice;

    private long klineCloseTime;

    private String quoteAssetVolume;

    private int numberOfTrades;

    private String takerBuyBaseAssetVolume;

    private String takerBuyQuoteAssetVolume;

    private String ignore;

}