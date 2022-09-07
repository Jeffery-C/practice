package com.systex.practice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MSTMB {

    @Id
    @Column
    private String stock;

    @Column(name = "StockName")
    private String stockName;

    @Column(name = "MarketType")
    private String marketType;

    @Column(name = "CurPrice")
    private BigDecimal curPrice;

    @Column(name = "RefPrice")
    private BigDecimal refPrice;

    @Column
    private String currency;

    @Column(name = "ModDate")
    private String modDate;

    @Column(name = "ModTime")
    private String modTime;

    @Column(name = "ModUser")
    private String modUser;

    /**
     * according to marketType field
     *
     * @return T -> 上市, O -> 上櫃, R -> 興櫃, otherwise -> Unknown type
     */
    public String getMarketTypeWithTC() {
        switch (this.marketType) {
            case "T":
                return "上市";
            case "O":
                return "上櫃";
            case "R":
                return "興櫃";
            default:
                return "Unknown type";
        }
    }
}
