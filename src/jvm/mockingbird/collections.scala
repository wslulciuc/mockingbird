package mockingbird

import org.apache.avro.Schema
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

sealed trait EventCollection {
  type S <: Schema
  val schema: S
  val name: String

  def add(events: Seq[Event])(implicit ec: ExecutionContext): Future[Seq[Event]] =
    Future {
      ???
    }
}
object EventCollection {
  def apply(name: String): Option[EventCollection] = name match {
    case SensorCollection.name => Some(SensorCollection)
    case StatsCollection.name  => Some(StatsCollection)
    case _                     => None
  }
}

object SensorCollection extends EventCollection {
  type S = Schema
  val schema = ???
  val name   = "sensor"
}

object StatsCollection extends EventCollection {
  type S = Schema
  val schema = ???
  val name   = "stats"
}
