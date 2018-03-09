package edu.knoldus

import java.util.Properties

import edu.knoldus.models.Student
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class StudentProducer {
  def main(args: Array[String]): Unit = {
    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "src/main/scala/StudentSerializer.scala")
    val producer = new KafkaProducer[String, Student](props)
    for (i <- 1 to 20000) {
      producer.send(new ProducerRecord[String, Student]("students", "student" + i, Student(i, "student" + i)))
    }
  }

}
