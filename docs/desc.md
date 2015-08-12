./storm jar cuber-storage.jar com.seamtop.cuber.storage.kafka.CuberConsumer 192.168.126.130


./kafka-console-producer.sh --broker-list 192.168.126.130:9092 --topic test
./kafka-console-consumer.sh --zookeeper 192.168.126.130:2181 --topic test --from-beginning