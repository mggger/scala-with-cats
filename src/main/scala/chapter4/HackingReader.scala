package chapter4

import cats.data.Reader
import cats.syntax.applicative._

case class Db(
  usernames: Map[Int, String],
  passwords: Map[String, String]
)


object HackingReader {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db =>
      db.usernames.get(userId)
    )

  def checkPassword(
    username: String,
    password: String
  ): DbReader[Boolean] = Reader(
    db => db.passwords.get(username).contains(password)
  )

  def checkLogin(
    userId: Int,
    password: String
  ): DbReader[Boolean] =

    for {
      username <- findUsername(userId)
      valid <- username.map(
        username => checkPassword(username, password)
      ).getOrElse(
        false.pure[DbReader]
      )
    } yield {
      valid
    }


  def main(args: Array[String]): Unit = {
    val users = Map(
      1 -> "da",
      2 -> "ba",
      3 -> "aa"
    )

    val passwords = Map(
      "da" -> "wang",
      "ba" -> "li",
      "aa" -> "yes"
    )

    val db = Db(users, passwords)

    println(checkLogin(1, "aaawang").run(db))
  }


}
