CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    create_date TIMESTAMP,
    theme VARCHAR(255),
    mode VARCHAR(255),
    removed BOOLEAN
);
