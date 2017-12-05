sealed trait IntList {
//  def length: Int = this match {
//    case End => 0
//    case Pair(hd, tl) => 1 + tl.length
//  }
//
//  def product: Int = this match {
//    case End => 1
//    case Pair(hd, tl) => hd * tl.product
//  }
//
//  def sum: Int = this match {
//    case End => 0
//    case Pair(hd, tl) => hd + tl.sum
//  }
//  def double: IntList = this match {
//    case End => End
//    case Pair(hd, tl) => Pair(hd * 2, tl.double)
//  }

//  def fold(end: Int, f: (Int, Int) => Int): Int = this match {
//    case End => end
//    case Pair(hd, tl) => f(hd, tl.fold(f, end))
//  }

  def fold[T](end: T, f: (Int, T) => T): T = this match {
    case End => end
    case Pair(hd, tl) => f(hd, tl.fold(end, f))
  }

  def length: Int = fold[Int](0, (_, tl) => 1 + tl)
  def product: Int = fold[Int](1, (hd, tl) => hd * tl)
  def sum: Int = fold[Int](0, (hd, tl) => hd + tl)
  def double: IntList = fold[IntList](End, (hd, tl) => Pair(hd * 2, tl))
}

case object End extends IntList
case class Pair(head: Int, tail: IntList) extends IntList

val example = Pair(1, Pair(2, Pair(3, End))) // 1, 2, 3, End
example.length
example.sum
