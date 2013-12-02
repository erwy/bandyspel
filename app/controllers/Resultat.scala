package controllers

import play.api.mvc._

object Resultat extends Controller{

  def info = Action {
    Ok(views.html.resultat(1))
  }

  def report(gameid: Long, homegoals: Int, awaygoals: Int, played: Boolean) = Action {
    models.Game.setresult(gameid,homegoals,awaygoals, played)
    Ok(views.html.games(models.Game.all()))
  }


}
