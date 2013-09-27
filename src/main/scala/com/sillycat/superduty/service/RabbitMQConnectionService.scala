package com.sillycat.superduty.service

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.QueueingConsumer
import com.rabbitmq.client.Address

object RabbitMQConnectionService {

  val TASK_QUEUE_NAME = "task_queue"

  //val SERVER_HOST = "localhost"

  def connect = {
    val factory = new ConnectionFactory
    //factory.setHost(SERVER_HOST)
    //val conn = factory.newConnection()

    val addrArr = Array(new Address("localhost", 5672), new Address("", 5673))
    val conn = factory.newConnection(addrArr)

    val channel = conn.createChannel()

    val durable = true
    channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null)

    val prefetchCount = 1
    channel.basicQos(prefetchCount)

    val consumer = new QueueingConsumer(channel)
    val autoAck = false

    channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer)

    (conn, channel, consumer)
  }
}
