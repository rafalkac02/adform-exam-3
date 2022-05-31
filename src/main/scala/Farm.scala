import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.io.Source.fromFile
import java.lang.Math.round

object Farm extends App {

  def getMonth(s: String) = s(5) match {
    case '0' => s(6).asDigit
    case _ => s.slice(5, 7).toInt
  }

  val harvest = "./harvest.csv"
  val prices = "./prices.csv"

  val harvestData = fromFile(harvest)
    .getLines
    .toSeq
    .drop(1)
    .map(line => line.split(","))

  val pricesData = fromFile(prices)
    .getLines()
    .toSeq
    .drop(1)
    .map(line => line.split(","))


  val monthlyData = new Array[collection.mutable.Map[String, Double]](12)
//  harvestData.foreach{ line =>
//    val month = getMonth(line(1))
//    val fruit = line(2)
//    val amount = line(3).toDouble
//
//    monthlyData(month-1)
//    monthlyData(month-1) += (fruit -> (monthlyData(month-1).getOrElse(fruit, 0.0) + amount))
//  }
//  monthlyData
//    .foreach{ println()
//      _.toSeq
//    .sortBy(x => x._2)
//    .map {case (fruit, amount) => (fruit, round(amount))}
//    .reverse
//    .foreach(println)
//}



  // CURRENT VERSION
  // PRINT OUT RANKING OF FRUITS HARVESTED SORTED BY AMOUNT

  val fruitsTotal = collection.mutable.Map.empty[String, Double]

  harvestData.foreach{ line =>
    val month = line(1).slice(5, 7)
    val fruit = line(2)
    val amount = line(3).toDouble
    fruitsTotal += (fruit -> (fruitsTotal.getOrElse(fruit, 0.0) + amount))
  }

  fruitsTotal
    .toSeq
    .sortBy(x => x._2)
    .map {case (fruit, amount) => (fruit, round(amount))}
    .reverse
    .foreach(println)
}