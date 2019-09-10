type Strategy = (Int, Int) => Int

// first-class functions
class Context(computer: Strategy) {
  def use(a: Int, b: Int): Int = computer(a, b)
}

val add: Strategy = _ + _
val multiply: Strategy = _ * _

new Context(add).use(2, 3)
new Context(multiply).use(2, 3)