/**
  * Find the last element of a list
  *
  * Example:
  * scala> last(List(1, 1, 2, 3, 5, 8))
  * res0: Int = 8
  */

val l = List(1, 1, 2, 3, 5, 8)

def last[A](l: List[A]): A = {
  l match {
    case h :: Nil => h
    case _ :: tail => last(tail)
    case _ => throw new NoSuchElementException
  }
}

assert(last(l) == 8)

l.take(2)
l.takeRight(2)


def secondLast[A](l: List[A]): A = {
  l match {
    case h :: _ :: Nil => h
//    case h :: List(t) => h
    case _ :: tail => secondLast(tail)
    case _ => throw new NoSuchElementException
  }
}

assert(secondLast(l) == 5)

// ------------------------------

1 :: 2 :: Nil
1 :: List(2)
1 :: 2 :: List.empty
1 :: 2 :: List()

// ------------------------------


def lastNth[A](n: Int, l: List[A]): A = {
  if (n <= 0) throw new IllegalArgumentException
  if (n > l.length) throw new NoSuchElementException
  l.takeRight(n).head
}

assert(lastNth(3, l) == 3)