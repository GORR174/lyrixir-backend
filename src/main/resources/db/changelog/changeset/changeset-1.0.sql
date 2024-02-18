CREATE TABLE artist (
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    profile_img varchar(255),
    created timestamp(6),
    updated timestamp(6)
);