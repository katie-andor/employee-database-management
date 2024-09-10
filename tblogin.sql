CREATE TABLE tblogin.EmployeeTable(
	employeeID int,
    fullName varchar(255),
    username varchar(255),
    pass varchar(255),
    PRIMARY KEY (employeeID)
);

INSERT INTO tblogin.EmployeeTable
VALUES (1, 'Joan of Arc', 'Joan', 'France');

INSERT INTO tblogin.EmployeeTable
VALUES (2, 'Lightning McQueen', 'Speed', 'Kachow');

SELECT * FROM tblogin.EmployeeTable;





CREATE TABLE tblogin.store_items(
	itemID int,
	itemName varchar(255),
    price varchar(255),
    leftInStock int,
    PRIMARY KEY (itemID)
);

SELECT * FROM tblogin.store_items;






CREATE TABLE tblogin.CustomerTable(
	customerID int,
    username varchar(255),
    pass varchar(255),
    fullName varchar(255),
    address varchar(255),
    PRIMARY KEY (customerID)
);

INSERT INTO tblogin.CustomerTable
VALUES (1, 'Walter', 'cooking', 'Walter White', '308 Negra Arroyo Lane');

INSERT INTO tblogin.CustomerTable
VALUES (2, 'Mater', 'hubcap', 'Tow Mater', 'Route 66');

SELECT * FROM tblogin.CustomerTable;


CREATE TABLE tblogin.coupons(
	couponCode varchar(255),
    discount varchar(255),
    expiryDate varchar(255),
    PRIMARY KEY (couponCode)
);

INSERT INTO tblogin.coupons
VALUES ('ABC', '15%', '01/01/25');

SELECT * FROM tblogin.coupons;







CREATE TABLE tblogin.orders(
	orderID int,
    customerID int,
    orderDate varchar(255),
    couponCode varchar(255),
    status varchar(255),
    PRIMARY KEY (orderID),
    FOREIGN KEY (customerID) REFERENCES CustomerTable(customerID),
    FOREIGN KEY (couponCode) REFERENCES coupons(couponCode)
);

INSERT INTO tblogin.orders
VALUES (1, 1, '03/24/24', 'ABC', 'shipped');

INSERT INTO tblogin.orders
VALUES (2, 2, '03/25/24', 'EFG', 'in the warehouse');

SELECT * FROM tblogin.orders;


