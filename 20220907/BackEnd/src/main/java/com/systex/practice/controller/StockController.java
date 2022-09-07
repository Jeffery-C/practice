package com.systex.practice.controller;

import com.systex.practice.controller.dto.response.StockDetailResponse;
import com.systex.practice.controller.dto.response.StockSummaryResponse;
import com.systex.practice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin("http://localhost:8090")
@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/detail")
    public List<StockDetailResponse> getStockDetailByCustSeqAndStock(
            @RequestParam String custSeq, @RequestParam String stock) throws ParseException {
        return this.stockService.getStockDetailByCustSeqAndStock(custSeq, stock);
    }

    @GetMapping("/summary")
    public List<StockSummaryResponse> getStockSummaryByCustSeq(@RequestParam String custSeq) throws ParseException {
        return this.stockService.getStockSummaryByCustSeq(custSeq);
    }

}
