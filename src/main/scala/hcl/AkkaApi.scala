package hcl

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import framework.dao.JdbcDao
//import org.apache.spark.sql.SparkSession


object AkkaApi extends App {

//    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()

    implicit val system = ActorSystem("HighLevelExample")
    implicit val materializer = ActorMaterializer()
    import system.dispatcher


    val simpleRoute =
      path("api" / "myEndpoint") {
        get 
          parameter('stock) { stockname: String =>
            val jdbcDao: JdbcDao = new JdbcDao()
            println(jdbcDao.historicRead(stockname))
            complete(StatusCodes.OK)
          }
      }
    Http().bindAndHandle(simpleRoute, "localhost", 8080)
}
