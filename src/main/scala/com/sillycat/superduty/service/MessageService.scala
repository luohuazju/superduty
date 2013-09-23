package com.sillycat.superduty.service

import com.typesafe.scalalogging.slf4j.Logging
import org.joda.time.DateTime
import org.quartz.JobKey

object MessageService extends Logging {

  def storePushNotification(campaignName: String, deviceNum: Int) = {
    val params = Map(
      "CAMPAIGN_NAME" -> campaignName,
      "JOB_NUM" -> deviceNum.toString,
      "SUB_JOB_CLASS_NAME" -> "com.sillycat.superduty.jobs.SendMessageJob")

    SchedulerService.schedulerJobAndTrigger(
      campaignName,
      "ProductMessage",
      campaignName,
      "ProduceMessage",
      params,
      "com.sillycat.superduty.jobs.ProduceMessageJob",
      DateTime.now.plusMinutes(2))
  }

}
