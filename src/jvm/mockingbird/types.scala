package mockingbird

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto._
import io.finch._
import io.finch.circe._

case class Payload(value: Json)
object Payload {
  implicit val decoder: Decoder[Payload] = deriveDecoder[Payload]
  implicit val encoder: Encoder[Payload] = deriveEncoder[Payload]
}

case class Event(name: String)
object Event {
  object Sensor extends Event("sensor")
  object Stats  extends Event("stats")
}
