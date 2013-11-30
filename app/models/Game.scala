package models

import anorm._
import java.util.Date
import play.api.db.DB
import play.api.Play.current

case class Game(
                id: Long,
                hometeam_id: Long,
                awayteam_id: Long,
                hometeam: String,
                awayteam: String,
                gamenum: Long,
                date: Option[Date],
                homegoals: Int,
                awaygoals: Int)

object Game {
  val game = {
      SqlParser.get[Long]("id") ~
      SqlParser.getAliased[Long]("hometeam_id") ~
      SqlParser.getAliased[Long]("awayteam_id") ~
      SqlParser.getAliased[String]("a") ~
      SqlParser.getAliased[String]("b") ~
      SqlParser.get[Long]("matchnumber") ~
        SqlParser.get[Option[Date]]("datetime") ~
        SqlParser.get[Int]("homegoals") ~
      SqlParser.get[Int]("awaygoals") map {
      case id ~ ht_id ~ at_id ~ ht ~ awayteam ~ gamenum ~ date ~ homegoals ~ awaygoals => Game( id, ht_id, at_id, ht, awayteam,gamenum, date, homegoals, awaygoals)
    }
  }


  def all(): List[Game] = DB.withConnection("psql") {
    implicit c =>
      SQL("SELECT t1.name as a, t2.name as b,t1.id as hometeam_id, t2.id as awayteam_id, g.* FROM game g JOIN team t1 ON t1.id = g.hometeam_id JOIN team t2 ON t2.id = g.awayteam_id").as(game *)
  }

  def getForTeam(id: Long): List[Game] = DB.withConnection("psql") {
    implicit c =>
      SQL("SELECT t1.name as a, t2.name as b,t1.id as hometeam_id, t2.id as awayteam_id, g.* FROM game g JOIN team t1 ON t1.id = g.hometeam_id JOIN team t2 ON t2.id = g.awayteam_id WHERE g.hometeam_id = {id} or g.awayteam_id = {id} ORDER BY g.matchnumber").on("id"->id).as(game *)
  }


}
