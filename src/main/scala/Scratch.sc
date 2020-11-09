object IntImplicits {
  implicit class Happy(num: Int) {
    def yeah(): Unit = for (_ <- 1 to num) if (num > 0) println("Oh yeah!")
    def times(f: Int => Unit) = for (i <- 1 to num) yield f(i)
  }
}

import IntImplicits._

2.yeah()
3.yeah()
(-1).yeah()

3.times(i => println(s"Look - it's the number $i!"))

val X = "Foo"
// X: String = Foo
val Y = "Bar"
// Y: String = Bar
val Z = "Baz"
// Z: String = Baz


"Bar" match {
  case X | Y => "It's foo or bar!"
  case Z     => "It's baz!"
}

case class Person(fn: String, ln: String)
Person("Dave", "Gurnell") match {
  case p @ Person(_, s) => s"The person $p has the surname $s"
}

val a = 1 to 5
val b = 6 to 10

for {
  i <- a
  j <- b
} yield (i, j)

a zip b

val baz = (a, b).zipped map (_ + _)

val ga = raw"""this whole "thing" should be one string object $baz"""
val pa = """this whole "thing" should be one string object $baz"""

val username = "fdsa"
val password = "ppp"

val hfajs = raw"""
                 |org.apache.kafka.common.security.plain.PlainLoginModule required username="$username" password="$password"
      """.stripMargin

val l = List(1, 3, List(5, List(6, 7)), 8, List(9, 10))
def flatten(l: List[Any]): List[Any] = l flatMap {
  case ls: List[_] =>
    println(s"list: $ls")
    flatten(ls)
  case h =>
    println(s"val: $h, after conversion: ${List(h)}")
    List(h)
}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

val f = Future(1)(ExecutionContext.Implicits.global)

def p(i: Int): PartialFunction[Try[Any], Unit] = {
  case Success(_) => println(i)
  case Failure(_) => println(i * 3)
}

f.onComplete {
  println("complete")
//  case Success(_) => println(2)
//  case Failure(_) => println(2 * 3)
  p(3)
}(ExecutionContext.Implicits.global)