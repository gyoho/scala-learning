trait Animal
private class Dog extends Animal
private class Cat extends Animal

// factory == singleton object
object Animal {
  def apply(kind: String): Animal = kind match {
    case "dog" => new Dog()
    case "cat" => new Cat()
  }
}

Animal("dog")
