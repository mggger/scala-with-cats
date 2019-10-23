package chapter4

import cats.Id



object MonadicSecret {


  val idMonid = new MonadY[Id] {

    def pure[A](a: A): Id[A] = a

    def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] =
      func(value)

    override def map[A, B](value: Id[A])(func: A => B): Id[B] =
      func(value)

  }
}
