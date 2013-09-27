Draft Demo

[Documents](https://github.com/luohuazju/superduty/wiki)

run the rabbitmq example

>sudo sbin/rabbitmq-server


>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerRabbitMQ'
>sbt 'run-main com.sillycat.superduty.jobs.consumer.WorkerRabbitMQ'


>sbt 'run-main com.sillycat.superduty.jobs.producer.NewTaskRabbitMQ'