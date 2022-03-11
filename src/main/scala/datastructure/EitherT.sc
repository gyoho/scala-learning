import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

val x: EitherT[Future, String, Int] = EitherT[Future, String, Int](Future.successful(Right(1)))
val y: EitherT[Future, String, Int] = EitherT[Future, String, Int](Future.failed(new Exception("BOOM!!")))
val z: EitherT[Future, String, Int] = EitherT[Future, String, Int](Future.successful(Left("FOO!!")))
val t: EitherT[Future, String, Int] = EitherT[Future, String, Int](Future.successful(Right(2)))

val res = for {
  xx <- x
  tt <- t
  yy <- y
  zz <- z
} yield(xx, yy, zz, tt)

Await.ready(res.value, Duration.Inf).value.get

//val a: EitherT[Future, String, Int] = EitherT[Future, String, Int](Future.successful(Left("foo")))
//val f = a.leftFlatMap(_ => EitherT[Future, String, Int](Future.successful(Right(1))))
//Await.ready(f.value, Duration.Inf).value.get