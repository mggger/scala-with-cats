package chapter1

import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._


object EqualityInstance {

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] {
    (cat1, cat2) =>
      cat1.name === cat2.name && cat1.age === cat2.age
  }

}

//object main extends App {
//
//  val cat1       = Cat("Garfield", 38)
//  val cat2       = Cat("Heathcliff", 33)
//  val optionCat1 = Option(cat1)
//  val optionCat2 = Option.empty[Cat]
//
//  import EqualityInstance._
//
//  println(optionCat1 === optionCat2)
//}