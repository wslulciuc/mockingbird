package mockingbird.api

import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import mockingbird._

object Api extends App {
  val postEvents: Endpoint[String] = post("events") { Ok("chirp") }
  val postEvent: Endpoint[String] = post("events" :: string) {
    collection: String =>
      Ok("chirp")
  }
  val api = (postEvents :+: postEvent).toServiceAs[Application.Json]
  val server = Http.server.serve(":8080", api)
  Await.ready(server)
}
