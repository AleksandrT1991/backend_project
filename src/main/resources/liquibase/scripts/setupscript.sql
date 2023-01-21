-- liquibase formatted sql

-- changeset atsygulev:1
CREATE TABLE AdDto (
    id        bigserial primary key,
    File      text,
    pk        integer,
    price     integer,
    title     text
);
-- changeset atsygulev:2
CREATE TABLE LoginReq(
    password  text,
    username  text
);
-- changeset atsygulev:3
CREATE TABLE RegisterReq (
    username  text,
    password  text,
    firstName text,
    lastName  text,
    phone     integer,
    role      text
);
-- changeset atsygulev:4
CREATE TABLE UserDto
(
    id        bigserial primary key,
    phone     text,
    lastName  text,
    firstName text,
    email     text

);
-- changeset atsygulev:5
CREATE TABLE UserImageDto
(
    id        bigserial primary key,
    userId    integer,
    filePath  text,
    fileSize  bigint default 0,
    mediaType text
);




