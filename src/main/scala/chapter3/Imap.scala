package chapter3

trait Codec[A] {
  def encode(value: A): String

  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    val self = this
    new Codec[B] {
      def encode(value: B): String =
        self.encode(enc(value))

      def decode(value: String): B =
        dec(self.decode(value))
    }
  }
}

//object runner extends App {
//  implicit val stringCodec: Codec[String] =
//    new Codec[String] {
//      def encode(value: String): String = value
//
//      def decode(value: String): String = value
//    }
//
//  implicit val intCodec: Codec[Int] =
//    stringCodec.imap(_.toInt, _.toString)
//
//
//  def encode[A](value: A)(implicit c: Codec[A]): String =
//    c.encode(value)
//
//  def decode[A](value: String)(implicit c: Codec[A]): A =
//    c.decode(value)
//
//
//  implicit val booleanCodec: Codec[Boolean] =
//    stringCodec.imap(_.toBoolean, _.toString)
//
//
//  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] =
//    c.imap[Box[A]](Box(_), _.value)
//
//
//  val a = Box[Int](1)
//
//  encode(a)
//
//}


case class Box[A](value: A)