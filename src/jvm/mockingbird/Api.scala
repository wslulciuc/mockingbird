package mockingbird.api

import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import mockingbird._
import mockingbird.Request._

object Api extends App {
  val postEvent: Endpoint[String] = post("events" :: string :: jsonBody[Request]) {
    (collectionName: String, req: Request) =>
      EventCollection(collectionName) match {
        case Some(eventCollection) => Ok("chirp")
        case None                  => NotFound(new Exception(s"The collection $collectionName does not exists."))
      }
  }
  val postEvents: Endpoint[String] = post("events") { Ok("chirp") }

  val api    = (postEvent :+: postEvents).toServiceAs[Application.Json]
  val server = Http.server.serve(":8080", api)
  Await.ready(server)
}
