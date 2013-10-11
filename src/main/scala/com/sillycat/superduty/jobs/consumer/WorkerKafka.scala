//package com.sillycat.superduty.jobs.consumer
//
//import kafka.api.{ FetchRequestBuilder, FetchRequest }
//import kafka.javaapi.consumer.SimpleConsumer
//import kafka.javaapi.FetchResponse
//import kafka.javaapi.message.ByteBufferMessageSet
//import scala.collection.JavaConversions._
//import java.nio.ByteBuffer
//import com.typesafe.scalalogging.slf4j.Logging
//
//object WorkerKafka extends App with Logging {
//  val simpleConsumer: SimpleConsumer = new SimpleConsumer("localhost", 9092, 10000, 1024000, "worker")
//
//  val req: FetchRequest = new FetchRequestBuilder()
//    .clientId("worker")
//    .addFetch("test", 0, 0L, 100)
//    .build()
//
//  while (true) {
//    val fetchResponse: FetchResponse = simpleConsumer.fetch(req)
//    val messages: ByteBufferMessageSet = fetchResponse.messageSet("test", 0)
//
//    messages foreach { msg =>
//      val buffer: ByteBuffer = msg.message.payload
//      val messages = new Array[Byte](buffer.remaining())
//      val bytes = ByteBuffer.wrap(messages)
//      logger.debug("message=" + bytes.toString)
//    }
//  }
//
//}
