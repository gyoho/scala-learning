import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

def doSomething = 3

// version 1
val p = Promise[Int]()
val f = p.future

val r = doSomething
p success r

val a = f.map(_ * 2)


// version 2
val b = Future {
  doSomething
}.map(_ * 2)