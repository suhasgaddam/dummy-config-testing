import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Bob {
  def printer(number: Int): Unit = {
    println(s"Start: $number")
    Thread.sleep(1000)
    println(s"Finish: $number")
  }
}

object Futuring extends App {
  val seq: Seq[Future[Unit]] = (0 to 10).map(i => Future { Bob.printer(i) })
}
