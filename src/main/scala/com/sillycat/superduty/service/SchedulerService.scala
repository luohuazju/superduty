package com.sillycat.superduty.service

import com.typesafe.scalalogging.slf4j.Logging
import org.quartz._

import org.quartz.JobBuilder.newJob
import org.quartz.impl.StdSchedulerFactory
import org.quartz.TriggerBuilder.newTrigger
import org.joda.time.DateTime
import org.quartz.impl.matchers.GroupMatcher
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

object SchedulerService extends Logging {

  private val scheduler: Scheduler = StdSchedulerFactory.getDefaultScheduler()

  def getScheduler(): Scheduler = {
    scheduler
  }

  def schedulerJobAndTrigger(jobName: String, jobGroup: String, triggerName: String, triggerGroup: String, params: Map[String, String], className: String, fireTime: DateTime) = {
    val jobDataMap: JobDataMap = new JobDataMap()
    jobDataMap.putAll(params.asJava)
    val job: JobDetail = newJob(
      Class.forName(className).asInstanceOf[Class[Job]])
      .withIdentity(jobName, jobGroup).setJobData(jobDataMap).build()
    val trigger = newTrigger().withIdentity(triggerName, triggerGroup).startAt(fireTime.toDate()).build()
    getScheduler().scheduleJob(job, trigger)
  }

  //return all the jobs
  def getAllJobs(): List[String] = {
    val jobs = List[String]()
    getScheduler().getJobGroupNames().toList.foreach { groupName =>
      getScheduler().getJobKeys(GroupMatcher.jobGroupEquals(groupName)).toList.foreach { jobKey =>
        jobs ++ jobKey.getName
      }
    }
    jobs
  }

  //return all the triggers
  def getAllTriggers(): List[String] = {
    val triggers = List[String]()
    getScheduler().getTriggerGroupNames().toList.foreach { groupName =>
      getScheduler().getTriggerKeys(GroupMatcher.groupEquals(groupName)).toList.foreach { triggerKey =>
        triggers ++ triggerKey.getName
      }
    }
    triggers
  }

  //remove job and all its trigger
  def remoteJob(jobKey: JobKey) = {
    assert(jobKey == null, "jobKey can not be null.")
    getScheduler().deleteJob(jobKey)
  }

}
