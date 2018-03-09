package edu.knoldus

import java.util.Properties

import edu.knoldus.models.Student
import edu.knoldus.serialization.StudentSerializer
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object StudentProducer {
  def main(args: Array[String]): Unit = {
    val props = new Properties
    val x = new StudentSerializer
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "edu.knoldus.serialization.StudentSerializer")
    val producer = new KafkaProducer[String, Student](props)
    for (i <- 1 to 20000) {
      producer.send(new ProducerRecord[String, Student]("students", "student" + i, Student(i, "student" + i)))
    }
  }

}
