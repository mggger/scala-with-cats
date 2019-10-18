package chapter3

import cats.Functor


sealed trait Tree[+A]

case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

case class Leaf[A](value: A) extends Tree[A]


object TreeInstances {
  implicit def treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](fa: Tree[A])(f: A => B): Tree[B] =
      fa match {
        case Branch(left, right) =>
          Branch(map(left)(f), map(right)(f))
        case Leaf(value) =>
          Leaf(f(value))
      }
  }
}


//object Runner extends App {
//
//  import cats.syntax.functor._
//  import TreeInstances._
//
//  val t: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
//
//  println(t)
//  println(t.map(_ * 2))
//}