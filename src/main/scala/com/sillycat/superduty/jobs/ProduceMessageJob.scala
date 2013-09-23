package com.sillycat.superduty.jobs

import org.quartz.{ JobKey, JobExecutionContext, Job }
import com.typesafe.scalalogging.slf4j.Logging
import com.sillycat.superduty.service.SchedulerService
import org.joda.time.DateTime

class ProduceMessageJob extends Job with Logging {

  def execute(context: JobExecutionContext) =
    {
      Thread.sleep(10 * 1000)
      logger.debug("I am doing the Produce Message job. ClassName = " + this.getClass.getName)

      val campaignName = context.getJobDetail.getJobDataMap.getString("CAMPAIGN_NAME")
      val jobNum = context.getJobDetail.getJobDataMap.getString("JOB_NUM")
      val subJobClassName = context.getJobDetail.getJobDataMap.getString("SUB_JOB_CLASS_NAME")

      Range(1, jobNum.toInt, 1).foreach { num =>
        val subJob = campaignName + num
        SchedulerService.getScheduler().checkExists(new JobKey(subJob)) match {
          case true => //do nothing
          case false => {
            SchedulerService.schedulerJobAndTrigger(
              subJob,
              campaignName,
              subJob,
              campaignName,
              Map(
                "SUB_JOB_NUM" -> num.toString
              ),
              subJobClassName,
              DateTime.now.plusMinutes(2))
          }
        }
      }
    }
}
