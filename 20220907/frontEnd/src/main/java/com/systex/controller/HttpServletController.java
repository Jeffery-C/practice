package com.systex.controller;

import com.systex.controller.dto.response.StockDetailResponse;
import com.systex.controller.dto.response.StockSummaryResponse;
import com.systex.model.StockDetail;
import com.systex.model.StockSummary;
import com.systex.service.StockService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "MyServlet", urlPatterns = {"/stock/detail", "/stock/summary"})
public class HttpServletController extends HttpServlet {

    /**
     * format decimal thousand separator
     */
    private final DecimalFormat formatTS = new DecimalFormat(",##0.####");
    private final DecimalFormat formatTS40 = new DecimalFormat(",##0.0000");

    private final DecimalFormat formatTS20 = new DecimalFormat(",##0.00");

    private final StockService stockService = StockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] stringList = request.getServletPath().split("/");

        if ("detail".equals(stringList[2])) {
            String custSeq = request.getParameter("custSeq");
            String stock = request.getParameter("stock");
            List<StockDetail> stockDetailList = this.stockService.getStockDetail(custSeq, stock);

            BigDecimal totalQuantity = new BigDecimal(0);
            BigDecimal totalCost = new BigDecimal(0);
            BigDecimal totalNetAssetValue = new BigDecimal(0);
            for (StockDetail stockDetail : stockDetailList) {
                totalQuantity = totalQuantity.add(stockDetail.getQuantity());
                totalCost = totalCost.add(stockDetail.getCost());
                totalNetAssetValue = totalNetAssetValue.add(stockDetail.getNetAssetValue());
            }

            List<StockDetailResponse> stockDetailResponseList
                    = stockDetailList.stream().map(stockDetail -> StockDetailResponse.builder()
                    .tradeDate(stockDetail.getTradeDate())
                    .docSeq(stockDetail.getDocSeq())
                    .stock(stockDetail.getStock())
                    .stockName(stockDetail.getStockName())
                    .quantity(this.formatTS.format(stockDetail.getQuantity()))
                    .tradingPrice(this.formatTS40.format(stockDetail.getTradingPrice()))
                    .cost(this.formatTS.format(stockDetail.getCost()))
                    .netAssetValue(this.formatTS.format(stockDetail.getNetAssetValue()))
                    .build()).collect(Collectors.toList());

            request.setAttribute("stockDetailList", stockDetailResponseList);
            request.setAttribute("custSeq", custSeq);
            request.setAttribute("stock", stockDetailResponseList.get(0).getStock());
            request.setAttribute("stockName", stockDetailResponseList.get(0).getStockName());
            request.setAttribute("totalQuantity", this.formatTS.format(totalQuantity));
            request.setAttribute("totalCost", this.formatTS.format(totalCost));
            request.setAttribute("totalNetAssetValue", this.formatTS.format(totalNetAssetValue));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/stockDetail.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if ("summary".equals(stringList[2])) {
            String custSeq = request.getParameter("custSeq");
            List<StockSummary> stockSummaryList = this.stockService.getStockSummary(custSeq);

            BigDecimal totalCost = new BigDecimal(0);
            BigDecimal totalNetAssetValue = new BigDecimal(0);

            for (StockSummary stockSummary : stockSummaryList) {
                totalCost = totalCost.add(stockSummary.getCost());
                totalNetAssetValue = totalNetAssetValue.add(stockSummary.getNetAssetValue());
            }

            BigDecimal totalProfit = totalNetAssetValue.subtract(totalCost);
            BigDecimal totalRateOfReturn = totalProfit.multiply(new BigDecimal(100))
                    .divide(totalCost, 2, RoundingMode.DOWN);

            List<StockSummaryResponse> stockSummaryResponses
                    = stockSummaryList.stream().map(stockSummary -> StockSummaryResponse.builder()
                    .stock(stockSummary.getStock())
                    .stockName(stockSummary.getStockName())
                    .marketType(stockSummary.getMarketType())
                    .transactionType(stockSummary.getTransactionType())
                    .quantity(this.formatTS.format(stockSummary.getQuantity()))
                    .averagePrice(this.formatTS.format(stockSummary.getAveragePrice()))
                    .cost(this.formatTS.format(stockSummary.getCost()))
                    .price(this.formatTS.format(stockSummary.getPrice()))
                    .netAssetValue(this.formatTS.format(stockSummary.getNetAssetValue()))
                    .profit(this.formatTS.format(stockSummary.getProfit()))
                    .isProfitWarning(stockSummary.getProfit().doubleValue() < 0 ? "warning" : "")
                    .rateOfReturn(this.formatTS20.format(stockSummary.getRateOfReturn()))
                    .isRateOfReturnWarning(stockSummary.getRateOfReturn().doubleValue() < 0 ? "warning" : "")
                    .build()).collect(Collectors.toList());

            request.setAttribute("stockSummaryList", stockSummaryResponses);
            request.setAttribute("custSeq", custSeq);
            request.setAttribute("totalCost", this.formatTS.format(totalCost));
            request.setAttribute("totalNetAssetValue", this.formatTS.format(totalNetAssetValue));
            request.setAttribute("totalProfit", this.formatTS.format(totalProfit));
            request.setAttribute("isTotalProfitWarning", totalProfit.doubleValue() < 0 ? "warning" : "");
            request.setAttribute("totalRateOfReturn", this.formatTS20.format(totalRateOfReturn));
            request.setAttribute("isTotalRateOfReturnWarning", totalRateOfReturn.doubleValue() < 0 ? "warning" : "");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/stockSummary.jsp");
            dispatcher.forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response, "This is doPost!!!");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String text)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Greeting</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Servlet Greeting at " + request.getContextPath() + "</h1>");
            if (null != text) out.println("<h2>" + text + "</h2>");
            out.println("</body>");

            out.println("</html>");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response, null);
    }

}
