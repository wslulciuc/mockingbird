package com.mockingbird

import io.finch._
import com.twitter.finagle.Http
import com.twitter.util.Await

object Api extends App {
  val api = get("sing") { Ok("chirp") }
  val server = Http.server.serve(":8080", api.toServiceAs[Text.Plain])
  Await.ready(server)
}
