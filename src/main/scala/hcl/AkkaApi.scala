package hcl

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import framework.dao.JdbcDao


object AkkaApi extends App {
    implicit val system = ActorSystem("HighLevelExample")
    implicit val materializer = ActorMaterializer()
    import system.dispatcher


    val simpleRoute =
      path("api" / "myEndpoint") {
        get 
          parameter('stock) { stockname: String =>
            val jdbcDao: JdbcDao = new JdbcDao()
            jdbcDao.historicRead(stockname)
            complete(StatusCodes.OK)
          }
      }
    Http().bindAndHandle(simpleRoute, "localhost", 8080)
}
