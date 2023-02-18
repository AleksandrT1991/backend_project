-- liquibase formatted sql

-- changeset ytuzlukov:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    phone      TEXT,
    last_name  TEXT,
    first_name TEXT,
    email      TEXT,
    username   TEXT,
    password   TEXT,
    image      BIGINT,
    city       TEXT,
    reg_date   TEXT
);

CREATE TABLE user_images
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id),
    file_path  TEXT,
    byte       BYTEA,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);

CREATE TABLE ads
(
    pk          BIGSERIAL PRIMARY KEY,
    price       BIGINT,
    title       TEXT,
    description TEXT,
    phone       TEXT,
    email       TEXT,
    image       TEXT,
    user_id     BIGINT REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE ad_images
(
    id         BIGSERIAL PRIMARY KEY,
    ad         BIGINT REFERENCES ads (pk) ON DELETE CASCADE,
    file_path  TEXT,
    byte       BYTEA,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);

CREATE TABLE ad_comments
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE,
    created_at TEXT,
    pk         BIGINT REFERENCES ads (pk) ON DELETE CASCADE,
    text       TEXT
);