package com.systex.controller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class StockSummaryResponse {
    private String stock;
    private String stockName;
    private String marketType;
    private String transactionType;
    private String quantity;
    private String averagePrice;
    private String cost;
    private String price;
    private String netAssetValue;
    private String profit;
    private String isProfitWarning;
    private String rateOfReturn;
    private String isRateOfReturnWarning;
}
