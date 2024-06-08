CREATE TABLE song (
    id SERIAL NOT NULL PRIMARY KEY,
    artist_id integer NOT NULL REFERENCES artist(id),
    song_name varchar(255) NOT NULL UNIQUE,
    text text NOT NULL,
    text_vector tsvector NOT NULL,
    created timestamp(6),
    updated timestamp(6)
);

CREATE INDEX song_index ON song USING gin(text_vector)