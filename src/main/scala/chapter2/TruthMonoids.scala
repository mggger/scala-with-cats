package chapter2

trait Semigroup[A] {
  def combine(x: A, y: A): A


}

trait TruthMonoid[A] extends Semigroup[A] {
  def empty: A
}

object TruthMonoid {
  def apply[A](implicit monoid: TruthMonoid[A]) =
    monoid


  def associativeLaw[A](x: A, y: A, z: A)
    (implicit m: TruthMonoid[A]): Boolean =
  {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)
    (implicit m: TruthMonoid[A]): Boolean =
  {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }


  implicit val andMonoid: TruthMonoid[Boolean] = {
    new TruthMonoid[Boolean] {
      override def empty: Boolean = true

      override def combine(x: Boolean, y: Boolean): Boolean = x && y
    }
  }

  implicit val orMonoid: TruthMonoid[Boolean] = {

    new TruthMonoid[Boolean] {
      override def empty: Boolean = false

      override def combine(x: Boolean, y: Boolean): Boolean = x || y
    }
  }
}

//object Main extends App {
//
//  println(Monoid.associativeLaw(true, true, true)(Monoid.andMonoid))
//  println(Monoid.identityLaw(true)(Monoid.andMonoid))
//
//
//  println(Monoid.associativeLaw(true, false, true)(Monoid.orMonoid))
//  println(Monoid.identityLaw(false)(Monoid.orMonoid))
//}
