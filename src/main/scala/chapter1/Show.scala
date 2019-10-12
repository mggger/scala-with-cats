package chapter1


import cats.syntax.show._
import cats.Show


object SHowInstances {

  implicit val catShow: Show[Cat] =
    new Show[Cat] {

      def show(c: Cat): String = {
        s"name: ${c.name}, age: ${c.age}"
      }
    }
}


//object ChapterShow extends App {
//
//  import SHowInstances._
//
//  val c = new Cat("wang", 10)
//  println(c.show)
//}