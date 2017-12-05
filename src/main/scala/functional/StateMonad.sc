// scalastyle:off

trait State[S, A] { self =>

  def run(s: S): (S, A)


  def map[B](f: A => B): State[S, B] = State {
    s=>
      val (s1, a) = self.run(s)
      (s1, f(a))
  }

  def flatMap[B](f: A => State[S, B]): State[S, B] = State {
    s =>
      val (s1, a) = self.run(s)

      val st2: State[S, B] = f(a)
      st2.run(s1)
  }


}

object State {
  def apply[S,A](f: S => (S, A)): State[S,A] = new State[S,A] {
    override def run(s: S) = f(s)
  }
}



def st1(st: String): State[String, Int] =  State(s => (s+st, s.length))



def x(s1: String, s2: String, s3: String) = for {
  l1 <- st1(s1)
  l2 <- st1(s2)
  l3 <- st1(s3)
} yield (l1, l2, l3)


val xx = x("foo", "bar", "yar")

xx.run("") // execution


val xxx = List(st1("foo"), st1("bar"), st1("yar"))