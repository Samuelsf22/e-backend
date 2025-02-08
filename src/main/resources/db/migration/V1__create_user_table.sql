CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    create_date TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    last_seen TIMESTAMP NOT NULL
);