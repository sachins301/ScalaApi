package framework.dao
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.flatspec.AnyFlatSpec
import org.scalamock.scalatest.MockFactory
import framework.dao.JdbcDao

class JdbcDaoTest extends AnyFunSuite with MockFactory {
  test("Infra test") {
    assert(true)
  }
//  test("jdbc"){
//    val mockJdbc = mock[JdbcDao]
//
//    inAnyOrder{
//      (mockJdbc.historicRead _) expects("ibm") returning("test") once()
//    }
//
//
//
//  }
}