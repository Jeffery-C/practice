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
public class HCMIO {

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

    @Column
    private String stock;

    @Column(name = "BsType")
    private String bsType;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal qty;

    @Column
    private BigDecimal amt;

    @Column
    private BigDecimal fee;

    @Column
    private BigDecimal tax;

    @Column(name = "StinTax")
    private BigDecimal stinTax;

    @Column(name = "NetAmt")
    private BigDecimal netAmt;

    @Column(name = "ModDate")
    private String modDate;

    @Column(name = "ModTime")
    private String modTime;

    @Column(name = "ModUser")
    private String modUser;

}
