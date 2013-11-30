package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Team(id: Long, name: String)

object Team {
  val team = {
      SqlParser.get[Long]("id") ~
      SqlParser.get[String]("name") map {
      case id ~ name => Team(id, name)
    }
  }

  def all(): List[Team] =  DB.withConnection { implicit c =>
    SQL("select * from team").as(team *)
  }

  def get(id: Long): Team = DB.withConnection { implicit c =>
    SQL("select * from team where id={id}").on("id"->id).as(team *).head
  }

  def create(label: String) {}

  def delete(id: Long) {}

}
