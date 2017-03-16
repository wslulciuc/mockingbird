package mockingbird

import com.twitter.app.Flag
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.param.{Stats, Label}
import com.twitter.finagle.stats.Counter
import com.twitter.finagle.{Http, Service}
import com.twitter.server.TwitterServer
import com.twitter.util.{Await, FuturePool}
import io.circe.generic.auto._
import io.circe.syntax._
import io.finch._
import io.finch.{post => POST}
import io.finch.circe._
import mockingbird._
import mockingbird.SensorData._

import java.util.UUID

import scala.concurrent.ExecutionContext.Implicits.global

object Api extends TwitterServer {
  val port: Flag[Int] = flag("port", 8080, "TCP port for HTTP server")

  // POST /device/:device_id/sensor/:sensor/data
  val postSensorData: Endpoint[String] = POST(
      "device" :: uuid("device_id") :: "sensor" :: string("sensor_type") :: "data"
        :: jsonBody[SensorData]) { (id: UUID, stype: String, data: SensorData) =>
    stype match {
      case SensorType(stype) =>
        FuturePool.unboundedPool {
          val enriched = EnrichedSensorData(Device.Id(id), stype, data)
          SensorStream.add(Record(data = enriched))
          Ok("chirp")
        }
      case _ => throw new ApiError.InvalidSensorType(name)
    }
  }.handle {
    case e: ApiError.InvalidSensorType => BadRequest(e)
  }

  def main(): Unit = {
    log.info("Starting server...")
    val api = (postSensorData).toServiceAs[Application.Json]
    val server = Http.server
      .configured(Stats(statsReceiver))
      .configured(Label("MockingbirdServer"))
      .serve(s":${port()}", api)

    onExit {
      log.info("Closing server...")
      server.close()
    }

    Await.ready(adminHttpServer)
  }
}
