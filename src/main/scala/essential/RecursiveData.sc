sealed trait IntList {
  def length: Int = this match {
    case End => 0
    case Pair(hd, tl) => 1 + tl.length
  }

  def product: Int = this match {
    case End => 1
    case Pair(hd, tl) => hd * tl.product
  }

  def double: IntList = this match {
    case End => End
    case Pair(hd, tl) => Pair(hd * 2, tl.double)
  }
}
final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

import scala.annotation.tailrec

def sumSub(list: IntList): Int = list match {
    case End => 0
    case Pair(hd, tl) => hd + sum(tl)
  }

@tailrec
def sum(list: IntList, total: Int = 0): Int = list match {
    case End => total
    case Pair(hd, tl) => sum(tl, total + hd)
  }

val example = Pair(1, Pair(2, Pair(3, End)))


assert(sum(example) == 6)
assert(sum(example.tail) == 5)
assert(sum(End) == 0)

assert(example.length == 3)
assert(example.tail.length == 2)
assert(End.length == 0)

assert(example.product == 6)
assert(example.tail.product == 6)
assert(End.product == 1)

assert(example.double == Pair(2, Pair(4, Pair(6, End))))
assert(example.tail.double == Pair(4, Pair(6, End)))
assert(End.double == End)