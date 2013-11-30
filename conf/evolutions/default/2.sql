# Users schema

# --- !Ups

CREATE TABLE GAME (
  id SERIAL NOT NULL,
  datetime DATETIME NOT NULL,
  matchnumber INTEGER NOT NULL,
  hometeam_id BIGINT(20) NOT NULL,
  awayteam_id BIGINT(20) NOT NULL,
  homegoals INTEGER NOT NULL,
  awaygoals INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (hometeam_id)
  REFERENCES TEAM(id),
  FOREIGN KEY (awayteam_id)
  REFERENCES TEAM(id)
);

# --- !Downs

DROP TABLE GAME;