CREATE TABLE users
(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    image_url varchar(255),
    created_at DATETIME DEFAULT (CURRENT_DATE),
    PRIMARY KEY (id),
    UNIQUE(email)
);