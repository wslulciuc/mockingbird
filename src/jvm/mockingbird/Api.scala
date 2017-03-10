package mockingbird

import com.twitter.app.Flag
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.param.{Stats, Label}
import com.twitter.finagle.stats.Counter
import com.twitter.finagle.{Http, Service}
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.circe.generic.auto._
import io.finch._
import io.finch.{post => POST}
import io.finch.circe._
import mockingbird._
import mockingbird.Payload._

object Api extends TwitterServer {
  val port: Flag[Int] = flag("port", 8080, "TCP port for HTTP server")

  val events: Counter = statsReceiver.counter("events")

  val postEvent: Endpoint[String] = POST("events" :: string :: jsonBody[Payload]) {
    (collectionName: String, payload: Payload) =>
      EventCollection(collectionName) match {
        case Some(eventCollection) => events.incr(); Ok("chirp")
        case None                  => NotFound(new Exception(s"The collection $collectionName does not exists."))
      }
  }
  val postEvents: Endpoint[String] = POST("events") { Ok("chirp") }

  def main(): Unit = {
    log.info("Starting server...")
    val api = (postEvent :+: postEvents).toServiceAs[Application.Json]
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
