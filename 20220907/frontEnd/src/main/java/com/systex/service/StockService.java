package com.systex.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.systex.model.StockDetail;
import com.systex.model.StockSummary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class StockService {
    private static StockService stockService;

    /**
     * format decimal thousand separator
     */
    private final DecimalFormat formatTS = new DecimalFormat(",##0.####");
    private final DecimalFormat formatTS40 = new DecimalFormat(",##0.0000");

    private final DecimalFormat formatTS20 = new DecimalFormat(",##0.00");


    private StockService() {
    }

    public static StockService getInstance() {
        if (stockService == null) {
            synchronized (StockService.class) {
                if (stockService == null) stockService = new StockService();
            }
        }
        return stockService;
    }

    public String httpRequest(String url, String method) throws IOException {
        // Copy from https://www.baeldung.com/java-http-request
        // https://blog.csdn.net/cunchi4221/article/details/107477489

        // Creating a HttpUrlConnection
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        // Setting request headers
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");

        // Configuring Timeouts
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Reading the Response
        StringBuffer response = new StringBuffer();
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader inReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String inputLine = "";
            while ((inputLine = inReader.readLine()) != null) {
                response.append(inputLine);
            }
            inReader.close();
        } else {
            response.append("GET request fail. status=" + status);
        }
        return response.toString();
    }


    public List<StockDetail> getStockDetail(String custSeq, String stock) throws IOException {
        String url = "http://localhost:8080/api/v1/stock/detail?custSeq=" + custSeq + "&stock=" + stock;

        String response = this.httpRequest(url, "GET");
        List<StockDetail> stockDetailList = new Gson().fromJson(response, new TypeToken<List<StockDetail>>() {
        }.getType());
        return stockDetailList;
    }

    public List<StockSummary> getStockSummary(String custSeq) throws IOException {
        String url = "http://localhost:8080/api/v1/stock/summary?custSeq=" + custSeq;

        String response = this.httpRequest(url, "GET");
        List<StockSummary> stockSummaryList = new Gson().fromJson(response, new TypeToken<List<StockSummary>>() {
        }.getType());
        return stockSummaryList;
    }
}
