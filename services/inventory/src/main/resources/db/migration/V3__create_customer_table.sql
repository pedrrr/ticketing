CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

INSERT INTO ticketing.customers(name, email, address)
VALUES ("Chell", "Chell@testing", "Aperture Science Lab, ??");

