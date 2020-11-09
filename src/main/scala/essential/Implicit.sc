/**
  * Where to define implicit scope
  * 1. the companion object of List
  * 2. 		          				of Ordering
  * 3. 			            		of the type
  *
  * Priority: local scope > instance in companion object
  * Good practice: put it in companion object and import manually
  */


import scala.math.Ordering

final case class Rational(numerator: Int, denominator: Int)

// 1. local scope
implicit val ordering: math.Ordering[Rational] = Ordering.fromLessThan[Rational]{
  (a, b) => (a.numerator * b.denominator) < (b.numerator * a.denominator)
}

// 2. companion object - compiler finds it so don't have to import explicitly
object Rational {
  implicit val acs: Ordering[Rational] = Ordering.fromLessThan[Rational]{
    (a, b) => (a.numerator * b.denominator) < (b.numerator * a.denominator)
  }
}


assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted == List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
