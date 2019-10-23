package chapter4

import cats.Monad

object BranchMonad {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    leaf(value)

  val treeMonad = new Monad[Tree] {

    override def pure[A](value: A) = Leaf(value)

    override def flatMap[A, B](value: Tree[A])(fn: A => Tree[B]): Tree[B] =

      value match {
        case Branch(l, r) =>
          Branch(flatMap(l)(fn), flatMap(r)(fn))

        case Leaf(value) =>
          fn(value)
      }

    override def tailRecM[A, B](a: A)(func: A => Tree[Either[A, B]]): Tree[B] =
      flatMap(func(a)) {
        case Left(value) =>
          tailRecM(value)(func)

        case Right(value) =>
          Leaf(value)
      }
  }
}


sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

