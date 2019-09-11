trait Animal
private class Dog extends Animal
private class Cat extends Animal

// Dong and Cat can be created only through this Singleton object => Factory
object Animal {
  // factory method!!
  def apply(kind: String): Animal = kind match {
    case "dog" => new Dog()
    case "cat" => new Cat()
  }
}

Animal("dog")
