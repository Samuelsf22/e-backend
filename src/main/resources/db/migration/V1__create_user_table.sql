CREATE TABLE api_user (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    create_date TIMESTAMP DEFAULT now(),
    last_modified_date TIMESTAMP,
    last_seen TIMESTAMP,
    roles VARCHAR(255) NOT NULL,
    CONSTRAINT unique_username UNIQUE (username)
);