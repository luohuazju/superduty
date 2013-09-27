package com.sillycat.superduty.jobs.producer

import com.sillycat.superduty.service.RabbitMQConnectionService
import com.rabbitmq.client.MessageProperties

object NewTaskRabbitMQ extends App {

  val (conn, channel, consumer) = RabbitMQConnectionService.connect

  val message = "campaign"

  Range(1, 10, 1).foreach { num =>
    channel.basicPublish("", RabbitMQConnectionService.TASK_QUEUE_NAME,
      MessageProperties.PERSISTENT_TEXT_PLAIN, (message + num).getBytes());
  }
  channel.close
  conn.close
}
