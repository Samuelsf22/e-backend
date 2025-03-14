CREATE TABLE api_category (
     id BIGSERIAL PRIMARY KEY,
     public_id UUID NOT NULL,
     name VARCHAR(255) NOT NULL,
     CONSTRAINT unique_category_name UNIQUE (name)
);

CREATE TABLE api_product (
     id BIGSERIAL PRIMARY KEY,
     public_id UUID NOT NULL,
     name VARCHAR(255) NOT NULL,
     description TEXT NOT NULL,
     brand VARCHAR(255) NOT NULL,
     color VARCHAR(255) NOT NULL,
     price DECIMAL(10,2) NOT NULL,
     featured BOOLEAN NOT NULL,
     stock INTEGER NOT NULL,
     image_name VARCHAR(255) NOT NULL,
     image_url VARCHAR(255) NOT NULL,
     image_public_id VARCHAR(255) NOT NULL,
     category_id BIGINT NOT NULL,
     CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES api_category(id),
     CONSTRAINT unique_product_public_id UNIQUE (public_id)
);