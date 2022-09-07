package com.systex.practice.controller.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDetailResponse {
    private String tradeDate;
    private String docSeq;
    private String stock;
    private String stockName;
    private BigDecimal quantity;
    private BigDecimal tradingPrice;
    private BigDecimal cost;
    private BigDecimal netAssetValue;
}
