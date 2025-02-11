CREATE TABLE api_user (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    create_date TIMESTAMP DEFAULT now(),
    last_modified_date TIMESTAMP,
    last_seen TIMESTAMP,
    roles VARCHAR(50) NOT NULL,
    CONSTRAINT unique_email UNIQUE (email),
    CONSTRAINT unique_username UNIQUE (username)
);