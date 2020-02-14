# storm-helloworld
the simplest kafka-kafka processor.

# Deployment
Copy compiled jar to machine with has storm isntalled.
Create or update a java property file (`dev.properties`) with the following properties (update property values as required)
```
kafka.zookeeper.hosts: 127.0.0.1
kafka.broker.hosts: 127.0.0.1
kafka.input.topic: stormtopic
kafka.output.topic: parseroutput
```

Copy this file and src/main/resource/helloworld.yaml to the same directory on the machine 
```
storm jar HelloWorld-1.0-SNAPSHOT.jar  org.apache.storm.flux.Flux --remote helloworld.yaml --filter dev.properties
```

Verify correct topology operation by using the Storm GUI to monitor for errors messages.

# Testing
From a full dev test machine running a HDP3.1 Kafka broker, generate some dummy data with the following script.
```
for i in {1..1000}; do echo "foo" | /usr/hdp/3.1.4.0-315/kafka/bin/kafka-console-producer.sh --broker-list node1:6667 --topic stormtopic; sleep 5; done
```

Monitor the output of the hellow world topology by running
```
/usr/hdp/3.1.4.0-315/kafka/bin/kafka-console-consumer.sh --bootstrap-server node1:6667 --topic parseroutput
```




