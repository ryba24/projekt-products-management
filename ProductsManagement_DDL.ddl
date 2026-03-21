-- =========================
-- ENUMS (as lookup tables)
-- =========================

CREATE TABLE role_type (
    role VARCHAR(20) PRIMARY KEY
);

INSERT INTO role_type VALUES
('ADMIN'), ('MANAGER'), ('STAFF'), ('CUSTOMER'), ('GUEST');


CREATE TABLE payment_status (
    status VARCHAR(20) PRIMARY KEY
);

INSERT INTO payment_status VALUES
('UNPAID'), ('PARTIAL'), ('PAID');


CREATE TABLE transfer_status (
    status VARCHAR(20) PRIMARY KEY
);

INSERT INTO transfer_status VALUES
('REQUESTED'), ('APPROVED'), ('REJECTED'), ('IN_TRANSIT'), ('COMPLETED');


-- =========================
-- CORE USER STRUCTURE
-- =========================

CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255),
    password_hash VARCHAR(255),
    is_active BOOLEAN,
    role VARCHAR(20),
    FOREIGN KEY (role) REFERENCES role_type(role)
);

CREATE TABLE customer_profile (
    id BIGINT PRIMARY KEY,
    user_id BIGINT,
    phone VARCHAR(50),
    notes TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE address (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT,
    line1 VARCHAR(255),
    line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    FOREIGN KEY (customer_id) REFERENCES users(id)
);


-- =========================
-- PRODUCT & INVENTORY
-- =========================

CREATE TABLE category (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    parent_category_id BIGINT,
    FOREIGN KEY (parent_category_id) REFERENCES category(id)
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    sku VARCHAR(100),
    name VARCHAR(255),
    description TEXT,
    unit_price DECIMAL(10,2),
    is_active BOOLEAN,
    min_stock_threshold INT,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE warehouse (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255),
    capacity INT
);

CREATE TABLE inventory_item (
    id BIGINT PRIMARY KEY,
    warehouse_id BIGINT,
    product_id BIGINT,
    quantity BIGINT,
    reorder_threshold INT,
    FOREIGN KEY (warehouse_id) REFERENCES warehouse(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);


-- =========================
-- ORDER MANAGEMENT
-- =========================

CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    order_number VARCHAR(100),
    customer_id BIGINT,
    status VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    shipping_address_id BIGINT,
    total_amount DECIMAL(12,2),
    FOREIGN KEY (customer_id) REFERENCES users(id),
    FOREIGN KEY (shipping_address_id) REFERENCES address(id)
);

CREATE TABLE order_line (
    id BIGINT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT,
    unit_price DECIMAL(10,2),
    discount DECIMAL(10,2),
    tax DECIMAL(10,2),
    total DECIMAL(12,2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);


-- =========================
-- INVOICING
-- =========================

CREATE TABLE invoice (
    id BIGINT PRIMARY KEY,
    order_id BIGINT,
    invoice_number VARCHAR(100),
    issued_date DATE,
    due_date DATE,
    status VARCHAR(20),
    subtotal DECIMAL(12,2),
    tax DECIMAL(12,2),
    grand_total DECIMAL(12,2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (status) REFERENCES payment_status(status)
);

CREATE TABLE invoice_line (
    id BIGINT PRIMARY KEY,
    invoice_id BIGINT,
    product_id BIGINT,
    description TEXT,
    quantity INT,
    unit_price DECIMAL(10,2),
    tax DECIMAL(10,2),
    line_total DECIMAL(12,2),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);


-- =========================
-- STOCK TRANSFERS
-- =========================

CREATE TABLE stock_transfer (
    id BIGINT PRIMARY KEY,
    from_warehouse_id BIGINT,
    to_warehouse_id BIGINT,
    requested_by BIGINT,
    status VARCHAR(20),
    created_at TIMESTAMP,
    approved_at TIMESTAMP,
    FOREIGN KEY (from_warehouse_id) REFERENCES warehouse(id),
    FOREIGN KEY (to_warehouse_id) REFERENCES warehouse(id),
    FOREIGN KEY (requested_by) REFERENCES users(id),
    FOREIGN KEY (status) REFERENCES transfer_status(status)
);

CREATE TABLE stock_transfer_line (
    id BIGINT PRIMARY KEY,
    transfer_id BIGINT,
    product_id BIGINT,
    quantity INT,
    FOREIGN KEY (transfer_id) REFERENCES stock_transfer(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);