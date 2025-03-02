CREATE TABLE api_order (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES api_user(id)
);

CREATE TABLE api_ordered_product (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_public_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES api_order(id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_public_id) REFERENCES api_product(public_id),
    CONSTRAINT unique_order_product UNIQUE (order_id, product_public_id)
);