// structural recursion using Pattern Matching

sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] = this match {
    case Empty() => Empty()
    case Full(v) => fn(v)
  }

  def map[B](fn: A => B): Maybe[B] = this.flatMap(v => Full(fn(v)))
}

final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

// ------------------------------------------------

val e: Maybe[Int] = Empty[Int]()
val f: Maybe[String] = Full("foo")

e.flatMap(x => Full(x))
f.flatMap(x => Full(x))


// ------------------------------------------------

val mightFail1: Maybe[Int] = Full(1)
val mightFail2: Maybe[Int] = Full(2)
val mightFail3: Maybe[Int] = Empty()

mightFail1.flatMap(x => mightFail2.flatMap(y => mightFail3.flatMap(z => Full(x + y + z))))


// ------------------------------------------------

val list: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
list.map(_.flatMap(v => if(v % 2 == 0) Full(v) else Empty[Int]()))
list.map(maybe => maybe flatMap { x => if(x % 2 == 0) Full(x) else Empty[Int]()})