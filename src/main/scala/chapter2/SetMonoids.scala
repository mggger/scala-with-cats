package chapter2


trait SetMonoid[A] extends Semigroup[A] {
  def empty: A
}

object SetMonoid {
  def apply[A](implicit monoid: SetMonoid[A]) =
    monoid


  def associativeLaw[A](x: A, y: A, z: A)
    (implicit m: SetMonoid[A]): Boolean =
  {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)
    (implicit m: SetMonoid[A]): Boolean =
  {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }

  implicit def union[A]: SetMonoid[Set[A]] = {

    new SetMonoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]
      override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    }
  }

  implicit def insersectSemigroup[A]: Semigroup[Set[A]] =
    (x, y) => x intersect y
  
}
