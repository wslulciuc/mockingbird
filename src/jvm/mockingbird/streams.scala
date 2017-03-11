package mockingbird

import org.apache.avro.Schema
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

sealed trait DataStream {
  type S <: Schema
  val schema: S
  val name: String

  def add(rec: Record)(implicit ec: ExecutionContext): Future[Record] =
    Future {
      ???
    }

  def add(rec: Seq[Record])(implicit ec: ExecutionContext): Future[Seq[Record]] =
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
