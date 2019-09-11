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