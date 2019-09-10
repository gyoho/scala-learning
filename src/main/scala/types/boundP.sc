class  Animal
class Dog extends Animal
class Puppy extends Dog
class Haru extends Puppy


object AnimalCarerUpperBound {
  def display [T <: Dog](t: T) = t
}

object AnimalCarerLowerBound {
  def display [T >: Dog](t: T) = t
}

val animal = new Animal
val dog = new Dog
val puppy = new Puppy
val haru = new Haru


//AnimalCarerUpperBound.display(animal) compilation error
AnimalCarerUpperBound.display(dog)
AnimalCarerUpperBound.display(puppy)
AnimalCarerUpperBound.display(haru)


// Any is all super class (which String and Puppy extends)
AnimalCarerLowerBound.display(animal)
AnimalCarerLowerBound.display("kkkdd")
AnimalCarerLowerBound.display(dog)
AnimalCarerLowerBound.display(puppy) // type casting to Dog
AnimalCarerLowerBound.display(haru)  // type casting to Dog
