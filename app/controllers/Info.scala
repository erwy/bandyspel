package controllers

import play.api.mvc._

object Info extends Controller{
  def info = Action {
    Ok(views.html.info())
  }

}
