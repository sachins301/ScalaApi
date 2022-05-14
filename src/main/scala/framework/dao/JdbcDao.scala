package framework.dao

import java.sql.{Connection, DriverManager, ResultSet}
import org.json4s._
import org.json4s.native.JsonMethods.compact
import org.json4s.native.Serialization._
import org.json4s.native.Serialization
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import spray.json.DefaultJsonProtocol.seqFormat
import spray.json.enrichAny

import scala.collection.mutable.Map

class JdbcDao {

  val url = "jdbc:mysql://localhost:3306/testschema"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username = "root"
  val password = "password"
  var connection: Connection = null

  def historicRead(stock: String): String ={
    var data: String = ""
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery(s"select * from stockhistory a inner join stock b on a.stockid = b.stockid where stockname = '$stock' and date BETWEEN DATE_SUB(NOW(), INTERVAL 3 DAY) AND NOW()")
      while (rs.next) {
        data = data + s"${rs.getString("stockname")}, ${rs.getString("price")}, ${rs.getString("date")} \n"
      }
    } catch {
      case e: Exception => e.printStackTrace
    }
    connection.close
    data
  }

  def checkBalance(userid: Int): String ={

    var data: String = ""
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery(s"select * from balance where userid = $userid")
      while (rs.next) {
        data = s"${rs.getString("balance")}"
      }
    } catch {
      case e: Exception => e.printStackTrace
    }
    connection.close
    data
  }
}
