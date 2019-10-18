package chapter4

//import cats.Monad

import cats.Id
import cats.Monad
import cats.syntax.functor._
import cats.syntax.flatMap._

//object Main extends App {
//
//  import cats.syntax.either._
//
//
//  type LoginResult = Either[LoginError, User]
//
//  def handleError(error: LoginError) =
//    error match {
//      case UserNotFound(u) =>
//        println(s"User not found: $u")
//
//      case PasswordIncorrect(u) =>
//        println(s"Password incorrect: $u")
//
//      case UnexpectedError =>
//        println(s"Unexpected error")
//    }
//
//  val result1: LoginResult = User("Dave", "pass1").asRight
//
//  val result2: LoginResult = UserNotFound("dava").asLeft
//
//  result1.fold(handleError, println)
//
//  result2.fold(handleError, println)
//}
//
//
sealed trait LoginError extends Product with Serializable

final case class UserNotFound(username: String) extends LoginError

final case class PasswordIncorrect(username: String) extends LoginError

case object UnexpectedError extends LoginError

case class User(username: String, password: String)




