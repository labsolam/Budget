CREATE TABLE Category
(
    ID INT NOT NULL,
    Name VARCHAR NOT NULL
);

CREATE TABLE Transaction
(
    ID INT  NOT NULL,
    Name VARCHAR NOT NULL,
    Type ENUM('INCOME', 'EXPENDITURE'),
    Amount DECIMAL
);