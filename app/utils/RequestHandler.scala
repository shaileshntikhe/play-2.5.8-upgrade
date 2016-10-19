package utils

import play.api._
import play.api.mvc._
import play.api.http._
import play.api.routing.Router
import javax.inject._
/*
PLAY 2.5.X ASKS TO MOVE AWAY FROM GLOBAL STATE
REFER FOLLOWING DOCS:
https://www.playframework.com/documentation/2.5.x/Migration25
https://www.playframework.com/documentation/2.5.x/GlobalSettings
*/

@Singleton
class HttpRequestHandler @Inject() (errorHandler: HttpErrorHandler, router: Router, configuration: HttpConfiguration, filter: Filters) extends DefaultHttpRequestHandler(router, errorHandler, configuration, filter) {

  override def handlerForRequest(request: RequestHeader) = {
    router.routes.lift(request) match {
      case Some(handler) =>
        Logger.info(s"Serving request: ${request.path} for ${request.method}")
        (request, handler)
      case None => (request, Action(Results.NotFound))
    }
  }

}
