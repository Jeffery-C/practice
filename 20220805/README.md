# 陳易伯

## 2022 年 8 月 5 日 Order API 課程實作
[TOC]

### GET 請求

* 取得所有 Order
http://localhost:8080/api/v1/order

* 根據 seq 取得個別 Order
http://localhost:8080/api/v1/order/:seq

### POST 請求

* 使用 RequestBody 來新增 Order
http://localhost:8080/api/v1/order

### PUT 請求

* 根據 seq 和使用 RequestBody 來修改已存在的 Order
http://localhost:8080/api/v1/order/:seq

### DELETE 請求

* 根據 seq 來刪除已存在的 Order
http://localhost:8080/api/v1/order/:seq