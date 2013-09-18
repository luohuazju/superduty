package com.sillycat.superduty.action

import spray.routing.{ ExceptionHandler, HttpService }
import com.typesafe.scalalogging.slf4j.Logging
import spray.util.LoggingContext
import spray.http.StatusCodes._
import spray.routing.HttpService
import spray.routing.PathMatcher
import shapeless._
import spray.util.LoggingContext
import spray.routing.ExceptionHandler
import spray.http.StatusCodes._
import akka.actor.ActorSystem
import scala.concurrent.{ Await, Future }
import akka.io.IO
import spray.can.Http
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import com.typesafe.scalalogging.slf4j.Logging
import scala.concurrent.ExecutionContext.Implicits.global

trait BaseRouter extends HttpService with Logging {
  implicit def myExceptionHandler(implicit log: LoggingContext) =
    ExceptionHandler {
      case e: java.lang.IllegalArgumentException => ctx =>
        log.warning("Request {} could not be handled normally", ctx.request)
        logger.error("java.lang.IllegalArgumentException:" + e)
        ctx.complete(BadRequest, e.getMessage)
      case x => ctx =>
        log.warning("Request {} could not be handled normally", ctx.request)
        logger.error("java.lang.x:" + x)
        ctx.complete(BadRequest, x.getMessage)
    }
}
