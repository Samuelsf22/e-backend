CREATE TABLE "order" (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES table_user(id)
);

CREATE TABLE "ordered_product" (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES order(id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT pk_order_product PRIMARY KEY (order_id, product_id)
);