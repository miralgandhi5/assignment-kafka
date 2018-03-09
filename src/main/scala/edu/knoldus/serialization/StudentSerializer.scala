package edu.knoldus.serialization

import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

import edu.knoldus.models.Student
import org.apache.kafka.common.serialization.Serializer

class StudentSerializer extends Serializer[Student] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: Student): Array[Byte] = {
    try {
      val byteWriter = new ByteArrayOutputStream()
      val objectWriter = new ObjectOutputStream(byteWriter)
      objectWriter.writeObject(data)
      objectWriter.close()
      byteWriter.close()
      byteWriter.toByteArray
    } catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }


  }

  override def close(): Unit = {}
}
