trait Lis[+A] {
  def ::[B >: A](a: B): Lis[B]
}

case object NilL extends Lis[Nothing] {
  override def ::[B >: Nothing](a: B) = ConcatL(a, NilL)
}

case class ConcatL[A](h: A, tail: Lis[A]) extends Lis[A] {
  override def ::[B >: A](a: B) = ConcatL(a, this)
}

"hello" :: NilL

1 :: ConcatL[String]("hello" ,NilL)


trait Eq[-A] {
  def isEqual(a: A, b: A): Boolean
}


trait Animal {
  def age: Int
}


case class Dog(age: Int) extends Animal
case class Cat(age: Int) extends Animal


val animalEq = new Eq[Animal] {
  override def isEqual(a: Animal, b: Animal) = a.age == b.age
}


val c = Cat(12)
val d = Dog(2)
animalEq.isEqual(c, d)

def filter[A](l: List[A], a: A, eq: Eq[A]): List[A] = l.filter(eq.isEqual(a, _))

val cats = List(Cat(12), Cat(1), Cat(3))


filter(cats, Cat(3), animalEq)