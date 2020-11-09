/**
  * --- Class ---
  * class A(val a: String) {
  *   println("instantiated")
  *   def ...
  * }
  *
  * val a = new A("hello")
  * >> instantiated
  *
  * - class body is a primary constructor
  * - constructor is called when instantiating the class
  */

/**
  * -- Companion Object --
  *
  * object Hello {
  *   print("instantiated Hello, ")
  *   def hi = println("hi")
  * }
  *
  * Hello.hi --> "instantiated Hello, hi" (1st time)
  * Hello.hi --> "hi" (2nd time)
  *
  * - singleton object is instantiated the 1st time it gets accessed [lazy]
  *
  * ** Companion object can access private variables in its companion class
  * ** other way is true too
  */

class Animal(var name: String, var age: Int) {
  // auxiliary constructor
  def this(name: String) = this(name, 0)
  override def toString = s"$name is $age years old"
}

// calling Animal's auxiliary constructor
class Dog1(name: String) extends Animal(name) {
  println("Dog constructor called")
  println(s"This dog should be $age")
}

// calling Animal's primary constructor
class Dog2(name: String) extends Animal(name, 12) {
  println("...")
}

// compile time error
class Dog3(name: String, age: Int) extends Animal {
  println("...")
}

// Dog's property will pass to instantiate Animal

trait CvsReader[T]

trait CvsStringReader[T] extends CvsReader[T] {
  def contents: String
}

class GeoIpCvsStringReader[T](val contents: String) extends CvsStringReader[T]