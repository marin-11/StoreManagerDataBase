DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS inventory;

CREATE TABLE inventory (
    item_id INTEGER PRIMARY KEY,
    item_name TEXT NOT NULL,
    item_price REAL NOT NULL,
    current_stock INTEGER NOT NULL,
    discount REAL
);

CREATE TABLE orders (
    order_id INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id INTEGER NOT NULL,
    amount_ordered INTEGER NOT NULL,
    order_date TEXT NOT NULL,

    FOREIGN KEY (item_id) REFERENCES inventory(item_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);