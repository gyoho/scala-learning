sealed trait MyStream[+A] {
  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case MyStream.MyConst(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }
  def toList: List[A] = this match {
    case MyStream.MyConst(h, t) => h() :: t().toList
    case _ => List()
  }
}

object MyStream {
  case object MyEmpty extends MyStream[Nothing]
  case class MyConst[+A](h: () => A, t: () => MyStream[A]) extends MyStream[A]

  def empty[A]: MyStream[A] = MyEmpty
  def const[A](h: => A, t: => MyStream[A]): MyStream[A] = {
    lazy val head = h
    lazy val tail = t
    MyConst(() => head, () => tail)
  }
}

val ms = MyStream.const(1, MyStream.const(2, MyStream.const(3, MyStream.empty)))
val res1 = ms.foldRight(MyStream.empty[Int]){
  (h, acc) =>
    if (h % 2 != 0) {
      println(h)
      MyStream.const(h, acc)
    } else {
      println("Nil")
      MyStream.empty[Int]
    }
}
val res2 = res1.toList


val s = Stream(1,2,3)
val res3 = s.foldRight(Stream.empty[Int]){
  (h, acc) =>
    if (h % 2 != 0) {
      println(h)
      h #:: acc
    } else {
      println("foo")
      Stream.empty[Int]
    }
}
val res4 = res3.toList

//-------------------------------//

def p(n: Int): Boolean = {
  println(n)
  n == 2
}

def q(b: Boolean): Boolean = {
  println("bar")
  b
}

val l = 1 :: 2 :: 3 :: Nil
l.foldRight[Boolean](false){
  (h, acc) => p(h) || q(acc)
}

l.foldLeft[Boolean](false){
  (acc, h) => p(h) || q(acc)
}

val s = Stream(1,2,3)
s.foldRight[Boolean](false){
  (h, acc) => p(h) || q(acc)
}

s.foldLeft[Boolean](false){
  (acc, h) => p(h) || q(acc)
}
val ms = MyStream.const(1, MyStream.const(2, MyStream.const(3, MyStream.empty)))
ms.foldRight[Boolean](false){
  (h, acc) => p(h) || q(acc)
}
