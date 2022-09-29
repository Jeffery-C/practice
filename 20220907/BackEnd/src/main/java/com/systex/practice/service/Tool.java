package com.systex.practice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

    /**
     * @param dateString 20220831
     * @return 2022/08/31
     */
    public static String dateFormatConvert(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        Date date = dateFormat.parse(dateString);
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }

    public static BigDecimal calcNetAmt(String BsType, BigDecimal price, BigDecimal qty) {
        if ("S".equals(BsType)) qty = qty.multiply(new BigDecimal(-1));
        BigDecimal amt = new BigDecimal(-1).multiply(price).multiply(qty);

        BigDecimal fee = amt.abs().multiply(new BigDecimal("0.001425"))
                .setScale(0, RoundingMode.DOWN);
        BigDecimal tax = new BigDecimal(0);
        if ("S".equals(BsType)) {
            tax = amt.abs().multiply(new BigDecimal("0.003"))
                    .setScale(0, RoundingMode.DOWN);
        }

        return amt.subtract(fee).subtract(tax);
    }

}
