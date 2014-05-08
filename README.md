We have a requirement to deliver a lot of messages out to the devices.

These designs need to be scalable, fault-tolerant.

Based on what we have in our old system, my design is as follow:


[Documents](https://github.com/luohuazju/superduty/wiki)

[Campaign Message Center Design](https://github.com/luohuazju/superduty/wiki/Campaign-Message-Center-Design)

[Persist Schedule Job Design](https://github.com/luohuazju/superduty/wiki/Quartz-Schedule-Draft-Design)

Also some investigate demo for rabbitMQ and Kafka based on Scala.

run the rabbitmq example

>sudo sbin/rabbitmq-server


>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerRabbitMQ'
>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerRabbitMQ'


>sbt 'run-main com.sillycat.superduty.jobs.producer.NewTaskRabbitMQ'

run the kafka example
>sbt 'run-main com.sillycat.superduty.jobs.producer.NewTaskKafka'
>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerKafka'
