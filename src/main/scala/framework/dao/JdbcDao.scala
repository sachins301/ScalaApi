package framework.dao

import java.sql.{Connection, DriverManager}

class JdbcDao {
  def historicRead(stock: String): Unit ={
    val url = "jdbc:mysql://localhost:3306/testschema"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "password"
    var connection: Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery(s"select * from stockhistory a inner join stock b on a.stockid = b.stockid where stockname = '$stock'")
      while (rs.next) {
        println(s"${rs.getString("stockname")}   ${rs.getString("price")}   ${rs.getString("date")}   ")
      }
    } catch {
      case e: Exception => e.printStackTrace
    }
    connection.close
  }
}
