package com.sillycat.superduty.action

/**
 * Created with IntelliJ IDEA.
 * User: carl
 * Date: 9/18/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
trait SchedulerRouter extends BaseRouter {

  def proxyRoutes(version: Int) = {
    path("scheduler") {
      get {
        complete {
          "test"
        }
      }
    }
  }
}
