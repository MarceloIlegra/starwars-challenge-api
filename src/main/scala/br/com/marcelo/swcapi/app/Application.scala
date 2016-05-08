package br.com.marcelo.swcapi.app

import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

object Application extends AppServer

class AppServer extends HttpServer {

  override val defaultFinatraHttpPort: String = ":8080"

  override def configureHttp(router: HttpRouter) {
    router.add[HelloController]
  }
}

class HelloController extends Controller{

  get("/") { request: Request =>
    "hello heroku"
  }

}