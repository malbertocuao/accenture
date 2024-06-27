CREATE TABLE IF NOT EXISTS account (
    customer_id int NOT NULL,
    account_number int PRIMARY KEY,
    account_type varchar(100) NOT NULL,
    branch varchar(200) NOT NULL,
    balance real NOT NULL DEFAULT 0.0,
    created_date date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_date date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id int AUTO_INCREMENT PRIMARY KEY,
    account_number int NOT NULL,
    amount real NOT NULL DEFAULT 0.0,
    balance real NOT NULL DEFAULT 0.0,
    created_date date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_date date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);