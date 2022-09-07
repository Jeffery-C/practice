<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- fix Expression Language not working --%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="/frontEndPractice/css/stock.css">
    <title>Stock Detail</title>
</head>
<body>
    <div class="container">
        <h3>股票名稱：${ requestScope.stock } ${ requestScope.stockName }</h3>
    </div>
    <div class="container">
        <table>
            <thead>
                <tr class="greyBG">
                    <th>交易日期</th>
                    <th>帳務序號</th>
                    <th>庫存股數</th>
                    <th>成交價</th>
                    <th>原始成本</th>
                    <th>預估庫存淨值</th>
                </tr>
            </thead>
            <tbody>
                <%! int count = 0; %>
                <%! String isBlueBG = ""; %>
                <c:forEach var="stock" items="${ requestScope.stockDetailList }">
                    <% isBlueBG = count%2 == 0 ? "blueBG" : ""; %>
                    <tr class="<%= isBlueBG %>">
                        <td>${ stock.tradeDate }</td>
                        <td>${ stock.docSeq }</td>
                        <td class="number">${ stock.quantity }</td>
                        <td class="number">${ stock.tradingPrice }</td>
                        <td class="number">${ stock.cost }</td>
                        <td class="number">${ stock.netAssetValue }</td>
                    </tr>
                    <% count = count + 1; %>
                </c:forEach>
                <tr class="yellowBG">
                    <td></td>
                    <td>合計</td>
                    <td class="number">${ requestScope.totalQuantity }</td>
                    <td></td>
                    <td class="number">${ requestScope.totalCost }</td>
                    <td class="number">${ requestScope.totalNetAssetValue }</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="container">
        <a href="javascript:void(0);" onclick="location.href='/frontEndPractice/stock/summary?custSeq=${requestScope.custSeq}';">
            回到股票庫存總攬表
        </a>
    </div>
</body>
</html>