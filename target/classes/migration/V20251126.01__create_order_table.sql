-- V20251126.01__create_order_table.sql

CREATE TABLE IF NOT EXISTS orders (
    id VARCHAR(36) PRIMARY KEY,
    order_number VARCHAR(50) UNIQUE NOT NULL,
    customer_id VARCHAR(50) NOT NULL,
    customer_email VARCHAR(100) NOT NULL,
    total_amount DECIMAL(12, 2) NOT NULL CHECK (total_amount >= 0),
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    order_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    shipping_address VARCHAR(500) NOT NULL,
    process_instance_id VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK (payment_status IN ('PENDING', 'PAID', 'FAILED', 'REFUNDED')),
    CHECK (order_status IN ('PENDING', 'CONFIRMED', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED'))
    );

CREATE INDEX IF NOT EXISTS idx_order_number ON orders(order_number);
CREATE INDEX IF NOT EXISTS idx_customer_id ON orders(customer_id);
CREATE INDEX IF NOT EXISTS idx_order_status ON orders(order_status);
CREATE INDEX IF NOT EXISTS idx_payment_status ON orders(payment_status);
CREATE INDEX IF NOT EXISTS idx_created_at ON orders(created_at DESC);
CREATE INDEX IF NOT EXISTS idx_customer_created ON orders(customer_id, created_at DESC);