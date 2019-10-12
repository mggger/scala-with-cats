package chapter1


trait Printable[A] {
  def format(value: A): String
}



object PrintableInstance {

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String = value
    }

  implicit val intPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String = s"${value}"
    }

  implicit val catPrintable: Printable[Cat] =
    new Printable[Cat] {
      override def format(value: Cat): String = s"name: ${value.name}, age: ${value.age}"
    }
}


object PrintableSyntax {

  implicit class PrintOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      p.format(value)

    def print(implicit p: Printable[A]) = {
      println(format)
    }
  }

}

//object Main extends App {
//  val c = new Cat("wang", 11)
//  import PrintableSyntax._
//  import PrintableInstance._
//  c.print
//}
