import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object FizBuzz {
  def main(args: Array[String]): Unit = {
  }

  def fizzBuzzRecursive(xs: List[Int]): Stream[String] = {
    @scala.annotation.tailrec
    def core(xs: List[Int], acc: Stream[String]): Stream[String] =
      xs match {
        case Nil => Stream.empty
        case x :: tail =>
          if (x % 3 == 0 && x % 5 == 0) core(tail, acc :+ "FizzBuzz")
          else if (x % 3 == 0) core(tail, acc :+ "Fizz")
          else if (x % 5 == 0) core(tail, acc :+ " Buzz")
          else core(tail, acc)
      }

    core(xs, Stream.empty)
  }

  def fizzBuzzFeatures(): Future[List[Unit]] = {
    val futures =
      (1 to 100).toList.map { x =>
        if (x % 5 == 0 && x % 3 == 0) Future {
          println("FizzBuzz")
        }
        else if (x % 5 == 0) Future {
          println("Fizz")
        }
        else if (x % 3 == 0) Future {
          println("Buzz")
        }
        else Future {
          println()
        }
      }
    Future.sequence(futures)
  }
}
