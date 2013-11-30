package models

import anorm._
import java.util.Date
import play.api.db.DB
import play.api.Play.current

case class Game(
                id: Long,
                hometeam: String,
                awayteam: String,
                gamenum: Long,
                date: Date,
                homegoals: Int,
                awaygoals: Int)

object Game {
  val game = {
      SqlParser.get[Long]("id") ~
      SqlParser.getAliased[String]("a") ~
      SqlParser.getAliased[String]("b") ~
      SqlParser.get[Long]("matchnumber") ~
        SqlParser.get[Date]("datetime") ~
        SqlParser.get[Int]("homegoals") ~
      SqlParser.get[Int]("awaygoals") map {
      case id ~ ht ~ awayteam ~ gamenum ~ date ~ homegoals ~ awaygoals => Game( id, ht, awayteam,gamenum, date, homegoals, awaygoals)
    }
  }


  def all(): List[Game] = DB.withConnection {
    implicit c =>
      SQL("SELECT t1.name as a, t2.name as b, g.* FROM game g JOIN team t1 ON t1.id = g.hometeam_id JOIN team t2 ON t2.id = g.awayteam_id").as(game *)
  }

  def getForTeam(id: Long): List[Game] = DB.withConnection {
    implicit c =>
      SQL("SELECT t1.name as a, t2.name as b, g.* FROM game g JOIN team t1 ON t1.id = g.hometeam_id JOIN team t2 ON t2.id = g.awayteam_id WHERE g.hometeam_id = {id} or g.awayteam_id = {id}").on("id"->id).as(game *)
  }


}
