package utils

import play.api._
import play.api.Play.current
import play.api.mvc._
import akka.actor._
import akka.cluster.Cluster
import akka.actor.DeadLetter

/*
PLAY 2.5.X ASKS TO MOVE AWAY FROM GLOBAL STATE.
ALL CODE NEEDS TO BE MOVED FROM GLOBAL TO SOMECUSTOMER REQUEST HANDLER. REQUEST AUTHENTICATION CODE HAS BEEN MOVED TO utils.RequestHandler
REFER DOCS:
1. https://www.playframework.com/documentation/2.5.x/Migration25
2. https://www.playframework.com/documentation/2.5.x/GlobalSettings
*/
object Global extends WithFilters() {

  lazy val RELAY = ActorSystem(
    "RelaySystem",
    Play.application.configuration.getConfig("relay.system").get.underlying
  )

  override def onStart(app: Application) {
    super.onStart(app)
    // DON'T REMOVE THESE TWO LINES
    // THEY CAUSE THE EARLY INSTANTIATION OF RELAY SYSTEM
    Logger.logger.info(s"Started Relay System with name: ${RELAY.name}")
    val listener = RELAY.actorOf(Props(classOf[DeadListener]))
    RELAY.eventStream.subscribe(listener, classOf[DeadLetter])
    CassandraClient.session.init()
  }

  override def onStop(app: Application) {
    RELAY.shutdown()
    super.onStop(app)
  }

}

class DeadListener extends Actor with ActorLogging {
  def receive = {
    case d: DeadLetter => log.warning("Dead Letter from {} to {}\n with message {}", d.sender, d.recipient, d.message)
  }
}
