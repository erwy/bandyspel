package controllers
import play.api.mvc._
object Game extends Controller{
  def allgames = Action {
    Ok(views.html.games(models.Game.all()))
  }

  def games(teamid: Long) = TODO


}
