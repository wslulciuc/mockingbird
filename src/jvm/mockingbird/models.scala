package mockingbird

import com.twitter.util.Time
import io.circe.generic.auto._
import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder, Json}
import io.finch._
import io.finch.circe._

import java.util.UUID

case class Device(id: Device.Id)
object Device {
  case class Id(value: UUID) {
    def asString: String = value.toString
  }
}

case class SensorData(sent_at: Long, value: Double)
object SensorData {
  implicit val decoder: Decoder[SensorData] = deriveDecoder[SensorData]
  implicit val encoder: Encoder[SensorData] = deriveEncoder[SensorData]
}

case class EnrichedSensorData(device_id: Device.Id,
                              received_at: Long,
                              sent_at: Long,
                              sensor_type: SensorType,
                              value: Double)
object EnrichedSensorData {
  implicit val decoder: Decoder[EnrichedSensorData] = deriveDecoder[EnrichedSensorData]
  implicit val encoder: Encoder[EnrichedSensorData] = deriveEncoder[EnrichedSensorData]

  def apply(id: Device.Id, stype: SensorType, data: SensorData): EnrichedSensorData = {
    EnrichedSensorData(device_id = id,
                       received_at = Time.now.inMilliseconds,
                       sent_at = data.sent_at,
                       sensor_type = stype,
                       value = data.value)
  }
}

sealed case class SensorType(name: String)
object SensorType {
  object Temperature extends SensorType("temp")
  def unapply(name: String): Option[SensorType] = name match {
    case Temperature.name => Some(Temperature)
    case _                => None
  }
}
