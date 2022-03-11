sealed trait MyList[+A] {
  def foldRight[B](z: B)(f: (A, B) => B): B = this match {
    case MyList.MyConst(h, t) => f(h, t.foldRight(z)(f))
    case MyList.MyNil => z
  }
}

object MyList {
  case object MyNil extends MyList[Nothing]
  case class MyConst[+A](h: A, t: MyList[A]) extends MyList[A]

  def const[A](h: A, t: MyList[A]): MyList[A] = MyConst(h, t)
  def empty[A]: MyList[A] = MyNil
}

val ml = MyList.const(1, MyList.const(2, MyList.const(3, MyList.empty)))
val res = ml.foldRight(MyList.empty[Int]){
  (h, acc) =>
    if (h % 2 != 0) {
      println(h)
      MyList.MyConst(h, acc)
    } else {
      println("foo")
      MyList.empty[Int]
    }
}