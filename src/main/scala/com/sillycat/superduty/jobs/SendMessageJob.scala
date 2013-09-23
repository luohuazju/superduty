package com.sillycat.superduty.jobs

import org.quartz.{ JobExecutionContext, Job }
import com.typesafe.scalalogging.slf4j.Logging

class SendMessageJob extends Job with Logging {

  def execute(context: JobExecutionContext) =
    {
      Thread.sleep(10 * 1000)
      val subJobNum = context.getJobDetail.getJobDataMap.getString("SUB_JOB_NUM")
      logger.debug("I am doing the Send Message job. ClassName = " + this.getClass.getName + " dollars in your account = " + subJobNum)
    }
}
