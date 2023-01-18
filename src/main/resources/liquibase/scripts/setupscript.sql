-- liquibase formatted sql

-- changeset atsygulev:1
CREATE TABLE AdDto (
    authorId SERIAL,
    File text,
    pk integer,
    price integer,
    title text
);