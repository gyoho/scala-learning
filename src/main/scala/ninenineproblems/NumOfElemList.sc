/**
  * Find the number of elements of a list
  *
  * Example:
  * scala> length(List(1, 1, 2, 3, 5, 8))
  * res0: Int = 6
  */

val list = List(1, 1, 2, 3, 5, 8)

def length[A](l: List[A]): Int = l.foldLeft(0) { (acc, _) => acc + 1 }

length(list)
length(List())


def size[A](l: List[A]): Int = {
  def looper(acc: Int, l: List[A]): Int = l match {
    case Nil => acc
    case h :: t => looper(acc + 1, t)
    case _ => throw new NoSuchElementException
  }

  looper(0, l)
}

size(list)
size(List())