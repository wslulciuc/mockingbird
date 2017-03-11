package mockingbird

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto._
import io.finch._
import io.finch.circe._

case class SensorData(sent_at: Long, value: Double)
object SensorData {
  implicit val decoder: Decoder[SensorData] = deriveDecoder[SensorData]
  implicit val encoder: Encoder[SensorData] = deriveEncoder[SensorData]
}

sealed case class SensorType(name: String)
object SensorType {
  object Temperature extends SensorType("temp")
}

sealed case class Record(name: String)
object Record {
  object Sensor extends Record("sensor")
  object Stats  extends Record("stats")
}
