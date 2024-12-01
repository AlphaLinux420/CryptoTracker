package com.ch.cryptotracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "candlestick_data")
@Getter
@Setter
public class CandlestickDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String opentime;

    private String closetime;

    private double open;

    private double high;

    private double low;

    private double close;

    private Double volume;
}
