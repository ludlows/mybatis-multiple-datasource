# spring boot project compatible with multiple datasources

In this project, we are using two different MySQL databases as different datasources.

In the production scenario, people alse use two different types of database like MySQL and PostgreSQL as different datasources. 


## usage

create two databases in MySQL (`master` & `slave`).

then we need to create table `t_shop` in `master` and `slave` database, respectively, using the following SQL:

```SQL
CREATE TABLE `t_shop` (
  `id` int NOT NULL,
  `shop_name` char(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

next we initialize the data in `master.t_shop` table using the following SQL:
```SQL
INSERT INTO master.t_shop (id, shop_name) VALUES(1, 'MasterShop');
```
at last we initialize the data in `slave.t_shop` table using the following SQL:
```SQL
INSERT INTO slave.t_shop (id, shop_name) VALUES(1, 'SlaveShop');
```

## check the result
run the Main.java and enter the url "http://localhost:8080/getMasterShopByAnnotation?id=1" in your browser.
we can got json string
```json
{"id":1,"shopName":"MasterShop"}
```
next enter the url "http://localhost:8080/getSlaverShopByAnnotation?id=1" in your browser.
we got
```json
{"id":1,"shopName":"SlaverShop"}
```
