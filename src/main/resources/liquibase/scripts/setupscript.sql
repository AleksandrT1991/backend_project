-- liquibase formatted sql

-- changeset atsygulev:1
CREATE TABLE ads
(
    id    BIGSERIAL PRIMARY KEY,
    file  TEXT REFERENCES ad_images (file_path),
    pk    INTEGER,
    price INTEGER,
    title TEXT
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    phone      TEXT,
    last_name  TEXT,
    first_name TEXT,
    email      TEXT,
    username   TEXT,
    password   TEXT
);

CREATE TABLE user_images
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES users (id),
    file_path  TEXT,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);

CREATE TABLE ad_images
(
    id         BIGSERIAL PRIMARY KEY,
    ad_id      INTEGER REFERENCES ads (id),
    file_path  TEXT,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);




