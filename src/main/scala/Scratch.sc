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