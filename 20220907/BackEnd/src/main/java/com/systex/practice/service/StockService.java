package com.systex.practice.service;


import com.systex.practice.controller.dto.response.StockDetailResponse;
import com.systex.practice.controller.dto.response.StockSummaryResponse;
import com.systex.practice.model.HCMIORepository;
import com.systex.practice.model.MSTMBRepository;
import com.systex.practice.model.TCNUDRepository;
import com.systex.practice.model.entity.MSTMB;
import com.systex.practice.model.entity.TCNUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private HCMIORepository hcmioRepository;

    @Autowired
    private TCNUDRepository tcnudRepository;

    @Autowired
    private MSTMBRepository mstmbRepository;

    public List<StockDetailResponse> getStockDetailByCustSeqAndStock(String custSeq, String stock) throws ParseException {
        List<TCNUD> tcnudList = this.tcnudRepository.findByCustSeqAndStock(custSeq, stock);
        List<StockDetailResponse> stockDetailList = new ArrayList<>();
        for (TCNUD tcnud : tcnudList) {
            MSTMB mstmb = this.mstmbRepository.findByStock(tcnud.getStock());

            stockDetailList.add(
                    StockDetailResponse.builder()
                            .tradeDate(Tool.dateFormatConvert(tcnud.getTradeDate()))
                            .docSeq(tcnud.getDocSeq())
                            .stock(mstmb.getStock())
                            .stockName(mstmb.getStockName())
                            .quantity(tcnud.getRemainQty())
                            .tradingPrice(tcnud.getPrice())
                            .cost(Tool.calcNetAmt("B", tcnud.getPrice(), tcnud.getRemainQty()).abs())
                            .netAssetValue(Tool.calcNetAmt("S", mstmb.getCurPrice(), tcnud.getRemainQty()).abs())
                            .build()
            );
        }
        return stockDetailList;
    }

    public List<StockSummaryResponse> getStockSummaryByCustSeq(String custSeq) throws ParseException {
        List<TCNUD> tcnudList = this.tcnudRepository.findByCustSeq(custSeq);
        List<String> stockNameList = tcnudList.stream()
                .map(TCNUD::getStock)
                .distinct()
                .collect(Collectors.toList());
        List<StockSummaryResponse> stockSummaryList = new ArrayList<>();
        for (String stock : stockNameList) {
            MSTMB mstmb = this.mstmbRepository.findByStock(stock);
            StockSummaryResponse stockSummary = StockSummaryResponse.builder()
                    .stock(mstmb.getStock())
                    .stockName(mstmb.getStockName())
                    .marketType(mstmb.getMarketTypeWithTC())
                    .transactionType(TCNUD.transactionType)
                    .quantity(new BigDecimal(0))
                    .averagePrice(new BigDecimal(0))
                    .cost(new BigDecimal(0))
                    .price(mstmb.getCurPrice())
                    .netAssetValue(new BigDecimal(0))
                    .build();
            List<StockDetailResponse> stockDetailList = this.getStockDetailByCustSeqAndStock(custSeq, stock);

            for (StockDetailResponse stockDetail : stockDetailList) {
                stockSummary.setQuantity(stockSummary.getQuantity().add(stockDetail.getQuantity()));
                stockSummary.setAveragePrice(
                        stockSummary.getAveragePrice().add(
                                stockDetail.getTradingPrice().multiply(stockDetail.getQuantity())
                        )
                );
                stockSummary.setCost(stockSummary.getCost().add(stockDetail.getCost()));
                stockSummary.setNetAssetValue(stockSummary.getNetAssetValue().add(stockDetail.getNetAssetValue()));
            }
            stockSummary.setAveragePrice(stockSummary.getAveragePrice().divide(
                    stockSummary.getQuantity(), 2, RoundingMode.DOWN));
            stockSummary.setProfit(stockSummary.getNetAssetValue().subtract(stockSummary.getCost()));
            stockSummary.setRateOfReturn(
                    stockSummary.getProfit().multiply(new BigDecimal(100))
                            .divide(stockSummary.getCost(), 2, RoundingMode.DOWN)
            );
            stockSummaryList.add(stockSummary);
        }
        return stockSummaryList;
    }
}
