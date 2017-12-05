sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

object Calculator{
  def +(calc: Calculation, num: Int): Calculation = calc match {
    case Success(res) => Success(res + num)
    case Failure(reasn) => Failure(reasn)
  }
  def -(calc: Calculation, num: Int): Calculation = calc match {
    case Success(res) => Success(res - num)
    case Failure(reasn) => Failure(reasn)
  }
  def /(calc: Calculation, num: Int): Calculation = calc match {
    case Success(res) => if (num == 0) Failure("Division by zero") else Success(res / num)
    case Failure(reasn) => Failure(reasn)
  }
}


assert(Calculator.+(Success(1), 1) == Success(2))
assert(Calculator.-(Success(1), 1) == Success(0))
assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))

assert(Calculator./(Success(4), 2) == Success(2))
assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))