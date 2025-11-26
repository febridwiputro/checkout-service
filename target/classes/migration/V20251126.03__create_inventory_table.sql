-- V20251126.03__create_inventory_table.sql

CREATE TABLE IF NOT EXISTS inventory (
    id VARCHAR(36) PRIMARY KEY,
    product_id VARCHAR(36) UNIQUE NOT NULL,
    product_name VARCHAR(200) NOT NULL,
    available_stock INTEGER NOT NULL DEFAULT 0 CHECK (available_stock >= 0),
    reserved_stock INTEGER NOT NULL DEFAULT 0 CHECK (reserved_stock >= 0),
    total_stock INTEGER NOT NULL DEFAULT 0 CHECK (total_stock >= 0),
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK (total_stock = available_stock + reserved_stock)
    );

CREATE UNIQUE INDEX IF NOT EXISTS idx_inventory_product_id ON inventory(product_id);
CREATE INDEX IF NOT EXISTS idx_inventory_available_stock ON inventory(available_stock);
CREATE INDEX IF NOT EXISTS idx_inventory_product_name ON inventory(product_name);