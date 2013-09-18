package com.sillycat.superduty.action

import akka.actor.{ Actor, ActorSystem }
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.slf4j.Logging
import spray.routing.{ HttpService, Directives }
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.io.IO
import spray.can.Http
import spray.routing.{ Directives, HttpService }

import com.typesafe.config.ConfigFactory
import com.sillycat.superduty.service.SchedulerService

object SuperDutyServer extends App with Logging {

  implicit val system = ActorSystem()

  val config = ConfigFactory.load()

  val serverAddress: String = config.getString("server.address")
  val serverPort: Int = config.getInt("server.port")

  //start the API server
  val handler = system.actorOf(Props[SuperDutyServerActor])
  IO(Http) ! Http.Bind(handler, serverAddress, serverPort)

  //start the scheduler
  val scheduler = SchedulerService.getScheduler()
  scheduler.start()
}

class SuperDutyServerActor extends Actor with SuperDutyConnector {
  def actorRefFactory = context

  def receive = runRoute(baseRoute ~ buildinfo)
}

trait SuperDutyConnector extends HttpService
    with SchedulerRouter
    with Directives {

  def buildinfo = {
    handleExceptions(implicitly[spray.routing.ExceptionHandler]) {
      pathPrefix(Segment) { version =>
        path("bldinfo") {
          getFromResource("bldinfo.txt")
        }
      }
    }
  }

  val baseRoute = {
    proxyRoutes(1)
  }
}