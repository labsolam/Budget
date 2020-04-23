CREATE TABLE Category
(
    ID INT IDENTITY NOT NULL,
    Name VARCHAR NOT NULL,
    Budget DECIMAL NOT NULL
);

--CREATE TABLE Transactions
--(
--    ID INT IDENTITY NOT NULL,
--    Name VARCHAR NOT NULL,
--    Type ENUM('INCOME', 'EXPENDITURE'),
--    Amount DECIMAL NOT NULL
--);

CREATE TABLE Account
(
    ID INT IDENTITY NOT NULL,
    Name VARCHAR NOT NULL,
    Type ENUM('DEBIT', 'CREDIT', 'SAVINGS'),
    StartingBalance DECIMAL NOT NULL
);

CREATE TABLE Label
(
    ID INT IDENTITY NOT NULL,
    Name VARCHAR NOT NULL
);