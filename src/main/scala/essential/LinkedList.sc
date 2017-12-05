sealed trait LinkedList[T] {
  def length: Int = this match {
    case Empty() => 0
    case Pair(hd, tl) => 1 + tl.length
  }

  def contains(value: T): Boolean = this match {
    case Empty() => false
    case Pair(hd, tl) => if (hd == value) true else tl.contains(value)
  }

  def apply(n: Int): Result[T] = this match {
    case Empty() => Failure("Index out of bounds")
    case Pair(hd, tl) => if (n == 0) Success(hd) else tl.apply(n - 1)
  }
}
final case class Empty[T]() extends LinkedList[T]
final case class Pair[T](head: T, tail: LinkedList[T]) extends LinkedList[T]

val example = Pair(1, Pair(2, Pair(3, Empty())))


assert(example.length == 3)
assert(example.tail.length == 2)
assert(Empty().length == 0)


assert(example.contains(3) == true)
assert(example.contains(4) == false)
assert(Empty().contains(0) == false)
// This should not compile
// example.contains("not an Int")

sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

assert(example(0) == Success(1))
assert(example(1) == Success(2))
assert(example(2) == Success(3))
assert(example(3) == Failure("Index out of bounds"))

