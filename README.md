Draft Demo

[Documents](https://github.com/luohuazju/superduty/wiki)

[Campaign Message Center Design](https://github.com/luohuazju/superduty/wiki/Campaign-Message-Center-Design)

[Persist Schedule Job Design](https://github.com/luohuazju/superduty/wiki/Quartz-Schedule-Draft-Design)

run the rabbitmq example

>sudo sbin/rabbitmq-server


>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerRabbitMQ'
>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerRabbitMQ'


>sbt 'run-main com.sillycat.superduty.jobs.producer.NewTaskRabbitMQ'

run the kafka example
>sbt 'run-main com.sillycat.superduty.jobs.producer.NewTaskKafka'
>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerKafka'
