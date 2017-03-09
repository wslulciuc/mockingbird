package mockingbird

import java.nio.charset.StandardCharsets.UTF_8

sealed abstract class EventData(val data: String, val meta: EventData.Meta) {
  def asBytes: Array[Byte] = asString.getBytes(UTF_8)
  def asString: String = ???
  protected def enrich(root: Option[String] = None) = ???
}

object EventData {
  case class Meta(rcvdTimestamp: Long, device: String)
}
