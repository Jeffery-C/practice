package com.systex.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StockDetail {
    private String tradeDate;
    private String docSeq;
    private String stock;
    private String stockName;
    private BigDecimal quantity;
    private BigDecimal tradingPrice;
    private BigDecimal cost;
    private BigDecimal netAssetValue;
}
