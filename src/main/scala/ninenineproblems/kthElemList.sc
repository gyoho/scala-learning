/**
  * Find the Kth element of a list
  *
  * Example:
  * scala> nth(2, List(1, 1, 2, 3, 5, 8))
  * res0: Int = 2
  */

val list = List(1, 1, 2, 3, 5, 8)

def nth[A](k: Int, l: List[A]): A = k match {
  case 0 => l.head
  case n if n > 0  => nth(n - 1, l.tail)
  case _ => throw new NoSuchElementException
}

def nthPure[A](k: Int, l:List[A]):A = (k, l) match {
  case (0, head :: tail) => head
  case (n, head :: tail) if n > 0 => nthPure(n - 1, tail)
  case _ => throw new NoSuchElementException
}


def findKth[A](k: Int, l: List[A]): A = nthPure(k - 1, l)


findKth(1, list)
findKth(3, list)
findKth(6, list)
findKth(7, list)
