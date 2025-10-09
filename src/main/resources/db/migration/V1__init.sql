CREATE TABLE venues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    total_capacity BIGINT NOT NULL
);

CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    venue_id BIGINT NOT NULL,
    total_capacity BIGINT NOT NULL,
    left_capacity BIGINT NOT NULL,
    CONSTRAINT fk_event_venue FOREIGN KEY (venue_id) REFERENCES venues(id) ON DELETE CASCADE
);

INSERT INTO ticketing.venues (name, address, total_capacity)
VALUE("Cine Joia", "São Paulo, SP", 2000), ("Autódromo Interlagos", "São Paulo, SP", 100000);

INSERT INTO ticketing.events (name, venue_id, total_capacity, left_capacity)
VALUE("Pedro The Lion", 1, 1000, 1000), ("Radiohead", 2, 100000, 100000);