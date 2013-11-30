# Users schema

# --- !Ups

CREATE TABLE TEAM (
    id serial NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE TEAM;