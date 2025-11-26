-- V20251126.04__create_products_table.sql

CREATE TABLE IF NOT EXISTS products (
                                        id VARCHAR(36) PRIMARY KEY,
    product_name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    price DECIMAL(12, 2) NOT NULL CHECK (price >= 0),
    category VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX IF NOT EXISTS idx_products_name ON products(product_name);
CREATE INDEX IF NOT EXISTS idx_products_category ON products(category);
CREATE INDEX IF NOT EXISTS idx_products_active ON products(is_active);

-- Migrate existing inventory to products
INSERT INTO products (id, product_name, description, price, category, is_active, created_at, updated_at)
SELECT
    product_id,
    product_name,
    'Auto-migrated from inventory',
    CASE product_name
        WHEN 'Laptop Dell XPS 13' THEN 20000000
        WHEN 'iPhone 15 Pro' THEN 15000000
        WHEN 'Samsung Galaxy S24' THEN 12000000
        WHEN 'MacBook Pro M3' THEN 30000000
        WHEN 'iPad Air' THEN 8000000
        WHEN 'Sony WH-1000XM5' THEN 5000000
        WHEN 'AirPods Pro' THEN 5000000
        WHEN 'Samsung Galaxy Watch' THEN 4000000
        WHEN 'Logitech MX Master 3' THEN 1500000
        WHEN 'LG UltraWide Monitor' THEN 6000000
        WHEN 'Mechanical Keyboard' THEN 2500000
        WHEN 'Webcam Logitech C920' THEN 2000000
        WHEN 'USB-C Hub' THEN 1000000
        WHEN 'Portable SSD 1TB' THEN 3000000
        WHEN 'Gaming Mouse' THEN 800000
        ELSE 0
        END,
    CASE
        WHEN product_name LIKE '%Laptop%' OR product_name LIKE '%MacBook%' THEN 'Computers'
        WHEN product_name LIKE '%iPhone%' OR product_name LIKE '%Samsung Galaxy S%' THEN 'Smartphones'
        WHEN product_name LIKE '%iPad%' THEN 'Tablets'
        WHEN product_name LIKE '%Headphones%' OR product_name LIKE '%AirPods%' OR product_name LIKE '%WH-%' THEN 'Audio'
        WHEN product_name LIKE '%Watch%' THEN 'Wearables'
        WHEN product_name LIKE '%Mouse%' OR product_name LIKE '%Keyboard%' THEN 'Peripherals'
        WHEN product_name LIKE '%Monitor%' THEN 'Displays'
        WHEN product_name LIKE '%Webcam%' THEN 'Camera'
        ELSE 'Accessories'
        END,
    TRUE,
    created_at,
    updated_at
FROM inventory
    ON CONFLICT (id) DO NOTHING;