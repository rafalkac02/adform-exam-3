import Farm.getMonth
import org.scalatest._
import flatspec._
import matchers._

class FarmSpec extends AnyFlatSpec with should.Matchers {

  behavior of "getMonth()"
  it should "extract month from date (string)" in {
    getMonth("2020-01-30") shouldBe 1
    getMonth("2022-05-31") shouldBe 5
    getMonth("2022-12-31") shouldBe 12
    getMonth("2022-10-01") shouldBe 10
  }
}