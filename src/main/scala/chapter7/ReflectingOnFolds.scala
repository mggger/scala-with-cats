package chapter7

object ReflectingOnFolds {

  def exercise1 =
    List(1, 2, 3).foldLeft(List.empty[Int])((a, i) => i :: a)


  def exercise2: Boolean = {

    val data = List(1, 2, 3)

    val data1 = List(1, 2, 3).map(i => i + 1)

    val data2 = List(1, 2, 3).foldRight(List.empty[Int])((a, i) => (a + 1) :: i)


    data1 == data2
  }

  def main(args: Array[String]): Unit = {

    import scala.concurrent._
    import scala.concurrent.duration._
    import scala.concurrent.ExecutionContext.Implicits.global

    val hostnames = List(
      "alpha.example.com",
      "beta.example.com",
      "gamma.demp.com"
    )

    def getUptime(hostname: String): Future[Int] =
      Future(hostname.length * 60)

    val allUptimes: Future[List[Int]] =
      Future.traverse(hostnames)(getUptime)

    val res = Await.result(allUptimes, 1.second)
    println(res)
  }


}
