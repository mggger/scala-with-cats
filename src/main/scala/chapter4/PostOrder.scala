package chapter4

import cats.data.State
import cats.syntax.applicative._

object PostOrder {

  type CalcState[A] = State[List[Int], A]

  def operator(func: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {

      case b :: a :: tail =>
        val ans = func(a, b)

        (ans :: tail, ans)

      case _ =>
        sys.error("Fail!")
    }

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] {
      stack => (num :: stack, num)
    }

  def evalOne(sym: String): CalcState[Int] =

    sym match {
      case "+" => operator(_ + _)

      case "-" => operator(_ - _)

      case "*" => operator(_ * _)

      case "/" => operator(_ / _)

      case num => operand(num.toInt)
    }

  def evalall(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
      a.flatMap(_ => evalOne(b))
    }


//  def main(args: Array[String]): Unit = {
//    println(evalOne("42").runA(Nil).value)
//
//    val program = for {
//      _ <- evalOne("1")
//      _ <- evalOne("2")
//      ans <- evalOne("+")
//    } yield {
//      ans
//    }
//
//    println(program.runA(Nil).value)
//
//    println(evalall(List("1", "2", "+")).runA(Nil).value)
//  }
}
