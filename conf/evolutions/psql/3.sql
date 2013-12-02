# --- !Ups

ALTER TABLE GAME ADD COLUMN played boolean;

# --- !Downs

ALTER TABLE GAME DROP COLUMN played;