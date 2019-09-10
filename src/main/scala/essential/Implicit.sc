import scala.math.Ordering

final case class Rational(numerator: Int, denominator: Int)

object Rational {
  implicit val acs: Ordering[Rational] = Ordering.fromLessThan[Rational]{
    (a, b) => (a.numerator * b.denominator) < (b.numerator * a.denominator)
  }
}


assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted == List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
