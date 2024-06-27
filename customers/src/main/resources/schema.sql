CREATE TABLE IF NOT EXISTS customer (
    customer_id int AUTO_INCREMENT PRIMARY KEY,
    document varchar(20) UNIQUE,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    phone varchar(20) NOT NULL,
    address varchar(200) NOT NULL,
    created_date date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_date date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);