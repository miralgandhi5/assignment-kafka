package edu.knoldus.serialization

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import edu.knoldus.models.Student
import org.apache.kafka.common.serialization.Deserializer

class StudentDeserializer extends Deserializer[Student] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def close(): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): Student = {
    try {
      val byteReader = new ByteArrayInputStream(data)
      val objectReader = new ObjectInputStream(byteReader)
      val student = objectReader.readObject().asInstanceOf[Student]
      byteReader.close()
      objectReader.close()
      student

    } catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }
}
