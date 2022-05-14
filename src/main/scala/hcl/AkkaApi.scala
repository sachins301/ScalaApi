package hcl

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import framework.dao.JdbcDao
//import org.apache.spark.sql.SparkSession


object AkkaApi extends App {

//    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()

    implicit val system = ActorSystem("HighLevelExample")
    implicit val materializer = ActorMaterializer()
    import system.dispatcher

  val jdbcDao: JdbcDao = new JdbcDao()

    val simpleRoute =
      get {
        path("api" / "myEndpoint") {
          parameter('stock) { stockname: String =>
            complete(HttpResponse(status = StatusCodes.OK, entity = jdbcDao.historicRead(stockname)))
          }
        } ~
          path("api" / "balance"){
            parameter('userid.as[Int]){ userid: Int =>
              complete(HttpResponse(status = StatusCodes.OK, entity = jdbcDao.checkBalance(userid)))
            }
          }
      }
    Http().bindAndHandle(simpleRoute, "localhost", 8080)
}
