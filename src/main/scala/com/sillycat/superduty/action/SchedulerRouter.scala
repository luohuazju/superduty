package com.sillycat.superduty.action

import com.sillycat.superduty.service.MessageService

/**
 * Created with IntelliJ IDEA.
 * User: carl
 * Date: 9/18/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
trait SchedulerRouter extends BaseRouter {

  def proxyRoutes() = {
    path("scheduler") {
      get {
        complete {
          Range(1, 5, 1).foreach { num =>
            MessageService.storePushNotification(num.toString + "sillycat_store", 1000)
          }
          "Send the Job Success."
        }
      }
    }
  }
}
