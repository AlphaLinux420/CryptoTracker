package com.ch.cryptotracker.repository;

import com.ch.cryptotracker.model.CandlestickDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandlestickDataRepository extends JpaRepository<CandlestickDataEntity, Long> {
    List<CandlestickDataEntity> findBySymbol(String symbol);
}
