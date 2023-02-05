-- liquibase formatted sql

-- changeset atsygulev:1
CREATE TABLE ads
(
    id                INTEGER,
    pk                BIGSERIAL PRIMARY KEY,
    price             INTEGER,
    title             TEXT,
    description       TEXT,
    phone             TEXT,
    author_first_name TEXT,
    author_last_name  TEXT,
    email             TEXT,
    image             TEXT,
    author            INTEGER
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
    ad_pk      INTEGER REFERENCES ads (pk),
    file_path  TEXT,
    file_size  BIGINT DEFAULT 0,
    media_type TEXT
);

CREATE TABLE ad_comments
(
    author     INTEGER REFERENCES users (id),
    created_ad TEXT,
    pk         INTEGER REFERENCES ads (pk),
    text       TEXT,
    id         BIGSERIAL PRIMARY KEY
);




