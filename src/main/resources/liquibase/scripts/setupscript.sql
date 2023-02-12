-- liquibase formatted sql

-- changeset atsygulev:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    phone      TEXT,
    last_name  TEXT,
    first_name TEXT,
    email      TEXT,
    username   TEXT,
    password   TEXT,
    image      TEXT
);

CREATE TABLE user_images
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id),
    file_path  TEXT,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);

CREATE TABLE ads
(
    pk          BIGSERIAL PRIMARY KEY,
    price       INTEGER,
    title       TEXT,
    description TEXT,
    phone       TEXT,
    email       TEXT,
    image       TEXT,
    user_id     BIGINT REFERENCES users (id)
);

CREATE TABLE ad_images
(
    id         BIGSERIAL PRIMARY KEY,
    ad      BIGINT REFERENCES ads (pk),
    file_path  TEXT,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);

CREATE TABLE ad_comments
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id),
    created_at TEXT,
    pk         BIGINT REFERENCES ads (pk),
    text       TEXT
);




