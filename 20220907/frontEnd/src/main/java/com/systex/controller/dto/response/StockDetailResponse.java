package com.systex.controller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class StockDetailResponse {
    private String tradeDate;
    private String docSeq;
    private String stock;
    private String stockName;
    private String quantity;
    private String tradingPrice;
    private String cost;
    private String netAssetValue;
}
