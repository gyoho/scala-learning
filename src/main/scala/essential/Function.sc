def lift[A, B](f: A => B): Option[A] => Option[B] = (a: Option[A]) => a map f
def liftTypeOmit[A, B](f: A => B): Option[A] => Option[B] = a => a map f  // infers from the return type
def liftWildCard[A, B](f: A => B): Option[A] => Option[B] = _ map f

def max(a: Int, b: Int): Int = if (a > b) a else b
val maxFuncType: (Int, Int) => Int = max // the return type tells it is a function
val maxParam = (a: Int, b: Int) => max(a, b)  // substitute max with its definition will be maxFuncLiteral
val maxFuncLiteral = (a: Int, b: Int) => if (a > b) a else b
val maxWildCard = max _ // equivalent to maxParam, wild card makes it infers the types

val numbers = 1 to 5
val sum = numbers.reduce( (a: Int, b: Int) => a + b )
val sumTypeOmit = numbers.reduce( (a, b) => a + b ) // infers from numbers
val sumWildCard = numbers.reduce(_ + _) // wild card holder