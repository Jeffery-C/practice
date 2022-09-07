package com.systex.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StockSummary {
    private String stock;
    private String stockName;
    private String marketType;
    private String transactionType;
    private BigDecimal quantity;
    private BigDecimal averagePrice;
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal netAssetValue;
    private BigDecimal profit;
    private BigDecimal rateOfReturn;
}
