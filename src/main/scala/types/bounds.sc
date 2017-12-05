class Creature
class Animal(val name: String) extends Creature
class Dog(override val name: String, val breed: String) extends Animal(name)
class Puppy(override val name: String, override val breed: String, val age: Int) extends Dog(name, breed)


object AnimalCarerUpperBound {
  // can access name and breed  i.e) the properties of Dog
  def display [T <: Dog](t: T) = t.breed
}

object AnimalCarerLowerBound {
  // can access name            i.e) the property of Animal
  def display [T >: Puppy <: Animal](t: T) = t.name
}

val animal = new Animal("animal")
val dog = new Dog("dog", "chouchou")
val puppy = new Puppy("dog", "chouchou", 3)



//AnimalCarerUpperBound.display(animal)
AnimalCarerUpperBound.display(dog)
AnimalCarerUpperBound.display(puppy)



AnimalCarerLowerBound.display(animal)
AnimalCarerLowerBound.display(dog)
