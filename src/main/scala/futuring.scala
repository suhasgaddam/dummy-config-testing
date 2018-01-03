import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Bob {
  def printer(number: Int): Unit = {
    println(s"Start : $number")
    Thread.sleep(1000)
    println(s"Finish: $number")
  }
}

object State {
  import scala.collection.mutable

  type K = Int
  type V = String

  var store: mutable.Map[K, V] = mutable.Map.empty

  def put(key: K, value: V): Option[V] = store.put(key, value)

  def get: mutable.Map[K, V] = store.clone()

  def set(update: mutable.Map[K, V]): Unit = {
    store = update
  }

  def printer(number: Int): Unit = {
    println(s"Start : $number $store")
    // Thread.sleep(1000)
    println(s"Finish: $number $store")
  }
}

object Futuring extends App {
  val seq: Seq[Future[Unit]] = (0 to 10).map(i => Future { Bob.printer(i) })
}

object Stating extends App {
  val seq: Seq[Future[Unit]] = (0 to 10).map { i =>
    Future {
      val old = State.get
      State.put(i, i.toString)
      State.printer(i)
      State.set(old)
    }
  }
}
