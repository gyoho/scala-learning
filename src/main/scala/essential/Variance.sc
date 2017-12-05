sealed trait Sum[+A, +B] {
  def flatMap[C](fn: B => Sum[A, C]): Sum[A, C] =
    this match {
      case Failure(v) => Failure(v)
      case Success(v) => fn(v)
    }
}

final case class Failure[A](value: A) extends Sum[A, Nothing]
final case class Success[B](value: B) extends Sum[Nothing, B]


// need to pass super type of A as param
case class Box[+A](value: A) {
  def set[AA >: A](a: AA): Box[AA] = Box(a)
}