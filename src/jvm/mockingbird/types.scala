package mockingbird

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto._
import io.finch._
import io.finch.circe._

case class Request(payload: Json)
object Request {
  implicit val reqDecoder: Decoder[Request] = deriveDecoder[Request]
  implicit val reqEncoder: Encoder[Request] = deriveEncoder[Request]
}

case class Event(name: String)
object Event {
  object Sensor extends Event("sensor")
}
