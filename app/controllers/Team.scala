package controllers

import play.api.mvc._


object Team extends Controller{
  def teams = Action {
    Ok(views.html.teams(models.Team.all()))
  }

  def info(id: Long) = Action {
    Ok(views.html.teaminfo(models.Team.get(id), models.Game.getForTeam(id)))
  }


}
