package com.sillycat.superduty.service

import com.typesafe.scalalogging.slf4j.Logging
import org.quartz.Scheduler
import org.quartz.impl.StdSchedulerFactory

object SchedulerService extends Logging {

  private val scheduler: Scheduler = StdSchedulerFactory.getDefaultScheduler()

  def getScheduler(): Scheduler = {
    scheduler
  }

}
