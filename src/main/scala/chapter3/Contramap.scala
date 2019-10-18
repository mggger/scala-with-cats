package chapter3


trait Printable[A] {

  self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String =
        self.format(func(value))
    }
}


//object Runner extends App {
//
//  implicit val stringPrintable: Printable[String] =
//    new Printable[String] {
//      def format(value: String): String = "\"" + value + "\""
//    }
//
//  implicit val booleanPrintable: Printable[Boolean] = {
//    new Printable[Boolean] {
//      def format(value: Boolean): String = if (value) "yes" else "no"
//    }
//  }
//
//  implicit class PrintOps[A](value: A) {
//    def format(implicit p: Printable[A]): String =
//      p.format(value)
//
//    def print(implicit p: Printable[A]) = {
//      println(format)
//    }
//  }
//
//  println(true.format)
//}

