package chapter2


import cats.syntax.semigroup._

object CatsMonoids {

  import cats.Monoid
  import cats.instances.int._
  import cats.instances.option._

  def add[T](items: List[T])(implicit monoid: Monoid[T]): T =
    items.foldLeft(monoid.empty)(_ |+| _)

  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    def combine(x: Order, y: Order): Order =
      Order(
        x.totalCost + y.totalCost,
        x.quantity + y.quantity
      )

    def empty = Order(0, 0)
  }

  println(add(List(Order(1.0, 1.0), Order(2.0, 1.0))))

}


case class Order(totalCost: Double, quantity: Double)



