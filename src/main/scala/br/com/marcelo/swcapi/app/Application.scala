package br.com.marcelo.swcapi.app

import scala.util.Properties

import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

object Application extends AppServer

class AppServer extends HttpServer {

  val port = Properties.envOrElse("PORT", "8080").toInt

  override val defaultFinatraHttpPort: String = ":" + port

  override def configureHttp(router: HttpRouter) {
    router.add[HelloController]
  }
}

class HelloController extends Controller{

  get("/") { request: Request =>
    "hello heroku"
  }

}