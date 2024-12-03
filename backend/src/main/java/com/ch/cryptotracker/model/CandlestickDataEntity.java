package com.ch.cryptotracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "candlestick_data")
@Getter
@Setter
public class CandlestickDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
