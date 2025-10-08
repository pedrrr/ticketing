ALTER TABLE events
ADD COLUMN ticket_price DECIMAL(10, 2) NOT NULL;

UPDATE ticketing.events
SET ticket_price = 120.00
WHERE name = 'Pedro The Lion';

UPDATE ticketing.events
SET ticket_price = 340.00
WHERE name = 'Radiohead';