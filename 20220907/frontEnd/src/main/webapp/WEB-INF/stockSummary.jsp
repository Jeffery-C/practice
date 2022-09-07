<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- fix Expression Language not working --%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="/frontEndPractice/css/stock.css">
    <title>Stock Summary</title>
</head>
<body>
    <div class="container">
        <table>
            <thead>
                <tr class="greyBG">
                    <th>股票代號</th>
                    <th>股票名稱</th>
                    <th>市場別</th>
                    <th>交易別</th>
                    <th>庫存股數</th>
                    <th>成交均價</th>
                    <th>投資成本</th>
                    <th>市價</th>
                    <th>預估庫存淨值</th>
                    <th>預估損益金額</th>
                    <th>預估報酬率</th>
                </tr>
            </thead>
            <tbody>
                <%! int count = 0; %>
                <%! String isBlueBG = ""; %>
                <c:forEach var="stock" items="${ requestScope.stockSummaryList }">
                    <% isBlueBG = count%2 == 0 ? "blueBG" : ""; %>
                    <tr class="<%= isBlueBG %>">
                        <td>${ stock.stock }</td>
                        <td>
                            <a href="javascript:void(0);" onclick="location.href='/frontEndPractice/stock/detail?custSeq=${requestScope.custSeq}&stock=${stock.stock}';">
                                ${ stock.stockName }
                            </a>
                        </td>
                        <td>${ stock.marketType }</td>
                        <td>${ stock.transactionType }</td>
                        <td class="number">${ stock.quantity }</td>
                        <td class="number">${ stock.averagePrice }</td>
                        <td class="number">${ stock.cost }</td>
                        <td class="number">${ stock.price }</td>
                        <td class="number">${ stock.netAssetValue }</td>
                        <td class="number ${ stock.isProfitWarning }">${ stock.profit }</td>
                        <td class="number ${ stock.isRateOfReturnWarning }">${ stock.rateOfReturn }%</td>
                    </tr>
                    <% count = count + 1; %>
                </c:forEach>
                <tr class="yellowBG">
                    <td></td>
                    <td>合計</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="number">${ requestScope.totalCost }</td>
                    <td></td>
                    <td class="number">${ requestScope.totalNetAssetValue }</td>
                    <td class="number ${ isTotalProfitWarning }">${ requestScope.totalProfit }</td>
                    <td class="number ${ isTotalRateOfReturnWarning }">${ requestScope.totalRateOfReturn }%</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>