//package com.sillycat.superduty.jobs.producer
//
//import java.util.Properties
//
//import kafka.javaapi.producer.Producer
//import kafka.producer.{ KeyedMessage, ProducerConfig }
//
//object NewTaskKafka extends App {
//  val props2: Properties = new Properties()
//  props2.put("zk.connect", "localhost:2181")
//  props2.put("metadata.broker.list", "localhost:9092");
//  props2.put("serializer.class", "kafka.serializer.StringEncoder")
//  props2.put("zk.connectiontimeout.ms", "15000")
//
//  val config: ProducerConfig = new ProducerConfig(props2)
//
//  val producer: Producer[String, String] = new Producer[String, String](config)
//
//  val data = new KeyedMessage[String, String]("campaign", "test-message, it is ok")
//  producer.send(data)
//  producer.close
//}
