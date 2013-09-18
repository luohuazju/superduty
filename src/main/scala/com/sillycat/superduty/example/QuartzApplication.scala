package com.sillycat.superduty.example

import org.quartz.{ SchedulerException, Scheduler }
import org.quartz.impl.StdSchedulerFactory

object QuartzApplication extends App {
  try {
    // Grab the Scheduler instance from the Factory
    val scheduler: Scheduler = StdSchedulerFactory.getDefaultScheduler();

    // and start it off
    scheduler.start();

    scheduler.shutdown();

  } catch {
    case se: SchedulerException => println("Error:" + se.toString)
  }

}
