package mockingbird

import io.circe.Printer
import io.circe.syntax._
import java.nio.ByteBuffer
import org.apache.avro.Schema
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

case class PartitionKey(value: String)
case class Record(key: Record.Key, bytes: ByteBuffer)
object Record {
  case class Key(value: String) {
    override def toString = value
  }
  def apply(data: EnrichedSensorData): Record = {
    Record(key = Key(data.device_id.asString),
           bytes = Printer.noSpaces.prettyByteBuffer(data.asJson))
  }
}

sealed trait DataStream {
  type S <: Schema
  val schema: S
  val name: String

  def add(rec: Record)(implicit ec: ExecutionContext): Future[Record] =
    Future {
      ???
    }

  def add(recs: Seq[Record])(implicit ec: ExecutionContext): Future[Seq[Record]] =
    Future {
      ???
    }
}

object DataStream {
  def apply(name: String): Option[DataStream] = name match {
    case SensorStream.name => Some(SensorStream)
    case StatsStream.name  => Some(StatsStream)
    case _                 => None
  }
}

object SensorStream extends DataStream {
  type S = Schema
  val schema = ???
  val name   = "sensor"
}

object StatsStream extends DataStream {
  type S = Schema
  val schema = ???
  val name   = "stats"
}
