-- V20251126.05__insert_sample_data.sql

-- Insert sample inventory data with UUID for product_id
INSERT INTO inventory (id, product_id, product_name, available_stock, reserved_stock, total_stock, created_at, updated_at)
VALUES
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Laptop Dell XPS 13', 50, 0, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'iPhone 15 Pro', 100, 0, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Samsung Galaxy S24', 75, 0, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'MacBook Pro M3', 30, 0, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'iPad Air', 60, 0, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Sony WH-1000XM5', 120, 0, 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'AirPods Pro', 150, 0, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Samsung Galaxy Watch', 80, 0, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Logitech MX Master 3', 200, 0, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'LG UltraWide Monitor', 40, 0, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Mechanical Keyboard', 150, 0, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Webcam Logitech C920', 90, 0, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'USB-C Hub', 200, 0, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Portable SSD 1TB', 85, 0, 85, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid()::text, gen_random_uuid()::text, 'Gaming Mouse', 180, 0, 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample orders
INSERT INTO orders (
    id, order_number, customer_id, customer_email, total_amount,
    payment_method, payment_status, order_status, shipping_address,
    process_instance_id, created_at, updated_at
)
VALUES
    (
        gen_random_uuid()::text,
        'ORD-20241126-ABC123',
        'CUST-001',
        'john.doe@example.com',
        25000000,
        'CREDIT_CARD',
        'PAID',
        'CONFIRMED',
        'Jl. Sudirman No. 123, Jakarta Selatan, DKI Jakarta, 12190 - Phone: 081234567890',
        'process-001',
        CURRENT_TIMESTAMP - INTERVAL '5 days',
        CURRENT_TIMESTAMP - INTERVAL '5 days'
    ),
    (
        gen_random_uuid()::text,
        'ORD-20241126-DEF456',
        'CUST-002',
        'jane.smith@example.com',
        15000000,
        'BANK_TRANSFER',
        'PENDING',
        'PENDING',
        'Jl. Gatot Subroto No. 45, Jakarta Pusat, DKI Jakarta, 10270 - Phone: 081298765432',
        NULL,
        CURRENT_TIMESTAMP - INTERVAL '3 days',
        CURRENT_TIMESTAMP - INTERVAL '3 days'
    ),
    (
        gen_random_uuid()::text,
        'ORD-20241126-GHI789',
        'CUST-003',
        'bob.wilson@example.com',
        35000000,
        'E_WALLET',
        'PAID',
        'PROCESSING',
        'Jl. Thamrin No. 78, Jakarta Pusat, DKI Jakarta, 10310 - Phone: 081234598760',
        'process-002',
        CURRENT_TIMESTAMP - INTERVAL '2 days',
        CURRENT_TIMESTAMP - INTERVAL '2 days'
    ),
    (
        gen_random_uuid()::text,
        'ORD-20241127-JKL012',
        'CUST-001',
        'john.doe@example.com',
        8500000,
        'CREDIT_CARD',
        'PAID',
        'DELIVERED',
        'Jl. Sudirman No. 123, Jakarta Selatan, DKI Jakarta, 12190 - Phone: 081234567890',
        'process-003',
        CURRENT_TIMESTAMP - INTERVAL '1 day',
        CURRENT_TIMESTAMP - INTERVAL '1 day'
    ),
    (
        gen_random_uuid()::text,
        'ORD-20241127-MNO345',
        'CUST-004',
        'alice.brown@example.com',
        12000000,
        'COD',
        'PENDING',
        'SHIPPED',
        'Jl. Kuningan No. 99, Jakarta Selatan, DKI Jakarta, 12940 - Phone: 081223344556',
        'process-004',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );

-- Insert sample order items
INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    20000000,
    20000000,
    CURRENT_TIMESTAMP - INTERVAL '5 days'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241126-ABC123'
  AND i.product_name = 'Laptop Dell XPS 13'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    5000000,
    5000000,
    CURRENT_TIMESTAMP - INTERVAL '5 days'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241126-ABC123'
  AND i.product_name = 'Sony WH-1000XM5'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    15000000,
    15000000,
    CURRENT_TIMESTAMP - INTERVAL '3 days'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241126-DEF456'
  AND i.product_name = 'iPhone 15 Pro'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    30000000,
    30000000,
    CURRENT_TIMESTAMP - INTERVAL '2 days'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241126-GHI789'
  AND i.product_name = 'MacBook Pro M3'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    5000000,
    5000000,
    CURRENT_TIMESTAMP - INTERVAL '2 days'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241126-GHI789'
  AND i.product_name = 'AirPods Pro'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    2,
    1500000,
    3000000,
    CURRENT_TIMESTAMP - INTERVAL '1 day'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241127-JKL012'
  AND i.product_name = 'Logitech MX Master 3'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    2500000,
    2500000,
    CURRENT_TIMESTAMP - INTERVAL '1 day'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241127-JKL012'
  AND i.product_name = 'Mechanical Keyboard'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    3,
    1000000,
    3000000,
    CURRENT_TIMESTAMP - INTERVAL '1 day'
FROM orders o
    CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241127-JKL012'
  AND i.product_name = 'USB-C Hub'
    LIMIT 1;

INSERT INTO order_items (id, order_id, product_id, product_name, quantity, price, subtotal, created_at)
SELECT
    gen_random_uuid()::text,
    o.id,
    i.product_id,
    i.product_name,
    1,
    12000000,
    12000000,
    CURRENT_TIMESTAMP
FROM orders o
         CROSS JOIN inventory i
WHERE o.order_number = 'ORD-20241127-MNO345'
  AND i.product_name = 'Samsung Galaxy S24'
    LIMIT 1;