package edu.knoldus

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

object StudentConsumer {
  def main(args: Array[String]): Unit = {

    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "edu.knoldus.serialization.StudentSerializer")
    props.put("value.serializer", "src/main/scala/StudentSerializer.scala")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "edu.knoldus.serialization.StudentDeserializer")
    props.put("group.id", "studentGroup")
    props.put("auto.offset.reset", "earliest")
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(Collections.singletonList("students"))
    while (true) {
      val records = consumer.poll(5000)
      for (record <- records.asScala)
        println(record.value())


    }

  }


}
