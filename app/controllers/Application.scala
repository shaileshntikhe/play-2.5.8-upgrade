package controllers

import scala.concurrent.Future
import play.api.mvc.Controller
import play.api.Logger
import play.api.mvc.Action

object Application extends Controller {

  def ping = Action {
    Logger.info(s"Received PING request")
    Ok(<response> <status>success</status> <data>pong</data> </response>)
  }

}
