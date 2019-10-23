package chapter4


import cats.data.Writer
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import cats.syntax.applicative._
import cats.instances.vector._
import cats.syntax.writer._

object SlowWorking {

  type Logged[A] = Writer[Vector[String], A]

  def slowly[A](body: => A) =
    try body
    finally Thread.sleep(100)


  def factorial(n: Int): Logged[Int] = {

    for {
      ans <- if (n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }

//  def main(args: Array[String]): Unit = {
//    val Vector((logA, ansA), (logB, ansB)) = Await.result(Future.sequence(Vector
//    (Future(factorial(3).run),
//      Future(factorial(3).run)
//
//    )
//    ), 5 seconds
//    )
//
//    println(logA, ansA)
//
//  }
}
