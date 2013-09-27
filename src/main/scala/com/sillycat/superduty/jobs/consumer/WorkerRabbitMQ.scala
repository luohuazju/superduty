package com.sillycat.superduty.jobs.consumer

import com.sillycat.superduty.service.RabbitMQConnectionService
import com.rabbitmq.client.QueueingConsumer
import com.typesafe.scalalogging.slf4j.Logging

object WorkerRabbitMQ extends App with Logging {
  val (conn, channel, consumer) = RabbitMQConnectionService.connect

  while (true) {
    val delivery: QueueingConsumer.Delivery = consumer.nextDelivery()

    val message = new String(delivery.getBody())
    val deliverTag = delivery.getEnvelope.getDeliveryTag

    logger.debug("Worker get task=" + message + " delivery tag=" + deliverTag)
    channel.basicAck(deliverTag, false)
  }
}
