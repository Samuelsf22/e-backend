CREATE TABLE category (
     id BIGSERIAL PRIMARY KEY,
     public_id UUID NOT NULL,
     name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
     id BIGSERIAL PRIMARY KEY,
     public_id UUID NOT NULL,
     name VARCHAR(255) NOT NULL,
     description TEXT NOT NULL,
     brand VARCHAR(255) NOT NULL,
     color VARCHAR(255) NOT NULL,
     price DECIMAL(10,2) NOT NULL,
     featured BOOLEAN NOT NULL,
     stock INTEGER NOT NULL,
     category_id BIGINT NOT NULL,
     CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category(id)
);