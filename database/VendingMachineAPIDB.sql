DROP DATABASE IF EXISTS vendingmachineapiDB;
CREATE DATABASE vendingmachineapiDB;

USE vendingmachineapiDB;

CREATE TABLE item(
id INT PRIMARY KEY AUTO_INCREMENT,
price Decimal(5,2) NOT NULL,
amount INT NOT NULL,
productname VARCHAR(30)
);

INSERT INTO item(id,price,amount,productname)
VALUES(1,3.29,22,"Vegan Cake"),
(2,77.99,50,"Cat Robot"),
(3,188.99,2,"Fountain of Youth"),
(4,39.68,43,"40 Tuna Cans");

-- some queries
Update item
set amount=(amount-1)
where id=2;

Select*
From item;
