package com.systex.practice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HCMIOAndTCNUDCompositeKey.class)
@Entity
public class TCNUD {

    public static final String transactionType = "現股";

    @Id
    @Column(name = "TradeDate")
    private String tradeDate;

    @Id
    @Column(name = "BranchNo")
    private String branchNo;

    @Id
    @Column(name = "CustSeq")
    private String custSeq;

    @Id
    @Column(name = "DocSeq")
    private String docSeq;

    private String stock;

    private BigDecimal price;

    private BigDecimal qty;

    @Column(name = "RemainQty")
    private BigDecimal remainQty;

    private BigDecimal fee;

    private BigDecimal cost;

    @Column(name = "ModDate")
    private String modDate;

    @Column(name = "ModTime")
    private String modTime;

    @Column(name = "ModUser")
    private String modUser;

}
