class  Animal
class Dog extends Animal
class Puppy extends Dog
class M extends Puppy


object AnimalCarerUpperBound{
  def display [T <: Dog](t: T) = t
}

object AnimalCarerLowerBound{
  def display [T >: Dog](t: T) = t
}

val animal = new Animal
val dog = new Dog
val puppy = new Puppy
val m = new M


//AnimalCarerUpperBound.display(animal)
AnimalCarerUpperBound.display(puppy)
AnimalCarerUpperBound.display(dog)
AnimalCarerUpperBound.display(m)


// Any is all super class (which String and Puppy extends)
AnimalCarerLowerBound.display(animal)
AnimalCarerLowerBound.display("kkkdd")
AnimalCarerLowerBound.display(dog)
AnimalCarerLowerBound.display(puppy)
