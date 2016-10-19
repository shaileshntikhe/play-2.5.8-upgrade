package utils

import javax.inject._
import akka.stream.Materializer
import play.api.mvc.{Result, RequestHeader, Filter}
import play.api.Logger
import play.api.routing.Router.Tags
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.http.HeaderNames._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import java.util.{TimeZone, Calendar, Date}
import java.text.SimpleDateFormat
import play.api.http.DefaultHttpFilters
import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc.EssentialFilter

/*
THERE IS CHANGE IN FILTERS IN PLAY 2.5.X
REFER FOLLOWING DOCS:
1. https://www.playframework.com/documentation/2.5.x/Migration25
2. https://www.playframework.com/documentation/2.5.x/ScalaHttpFilters
WE NEED TO CREATE ONE CLASS WHICH IS EXTENDING DefaultHttpFilters AND PASS INSTANCE OF FILTER CLASS(THIS CLASS HAS TO EXTENDS FILTER CLASS, PROVIDE FILTERING IMPLEMENTATION AS PER YOUR REQUIREMENTS) TO DefaultHttpFilters CONSTRUCTOR
*/

@Singleton
class Filters @Inject() (customFilter: CustomFilter) extends DefaultHttpFilters(customFilter)

@Singleton
class CustomFilter @Inject() (implicit val mat: Materializer) extends Filter {
  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    nextFilter(requestHeader).map { result =>
      result.withHeaders(
        PRAGMA -> "no-cache",
        CACHE_CONTROL -> "no-cache, no-store, must-revalidate, max-age=0",
        EXPIRES -> serverTime
      )
    }
  }
  private def serverTime = {
    val calendar = Calendar.getInstance()
    val dateFormat = new SimpleDateFormat(
      "EEE, dd MMM yyyy HH:mm:ss z")
    dateFormat.setTimeZone(calendar.getTimeZone)
    dateFormat.format(calendar.getTime())
  }
}
