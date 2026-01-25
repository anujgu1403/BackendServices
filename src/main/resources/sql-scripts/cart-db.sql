-- V1: Create carts and cart_items tables for Postgres
CREATE DATABASE cartdb;

CREATE TABLE IF NOT EXISTS carts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT now(),
    is_active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE IF NOT EXISTS cart_items (
    id SERIAL PRIMARY KEY,
    item_id INTEGER NOT NULL,
    unit_price NUMERIC(18,2) NOT NULL,
    quantity INTEGER NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT now(),
    cart_id BIGINT NOT NULL REFERENCES carts(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_cart_user ON carts(user_id);
CREATE INDEX IF NOT EXISTS idx_cartitems_cartid ON cart_items(cart_id);

ALTER TABLE carts ALTER COLUMN user_id DROP NOT NULL;