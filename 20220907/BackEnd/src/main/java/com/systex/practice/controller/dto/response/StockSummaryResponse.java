package com.systex.practice.controller.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockSummaryResponse {

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
