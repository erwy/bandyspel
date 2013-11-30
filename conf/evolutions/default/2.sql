# Users schema

# --- !Ups

CREATE TABLE GAME (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  datetime DATETIME NOT NULL,
  matchnumber INTEGER NOT NULL,
  hometeam_id BIGINT(20) NOT NULL,
  awayteam_id BIGINT(20) NOT NULL,
  homegoals INTEGER NOT NULL DEFAULT 0,
  awaygoals INTEGER NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (hometeam_id)
  REFERENCES Team(id),
  FOREIGN KEY (awayteam_id)
  REFERENCES Team(id)
);

# --- !Downs

DROP TABLE GAME;