import cats.Monad
import cats.implicits._


// case class ValidatedCampaign(ecpmCampaign: EcpmCampaign, failures: Seq[ValidationResult])
type Valid[T] = Either[String, T]
// scalastyle:off

trait Kleisli[F[_], A, B] {
  def run(a: A): F[B]

  def apply(a: A): F[B] = run(a)

  def andThen[C](k: Kleisli[F, B, C])(implicit M: Monad[F]): Kleisli[F, A, C] = Kleisli {
    a =>
      val fb: F[B] = this.run(a)
      M.flatMap(fb)(b => k.run(b))
  }
}

object Kleisli {
  def apply[F[_], A, B](f: A => F[B]): Kleisli[F, A, B] = Kleisli(f)
}



type Validator[T] = Kleisli[Valid, T, T]


val k0 = (i: Int) => Either.cond(i >= 0,  i + 1, "Negative")
val k1: Validator[Int] = Kleisli(i => Either.cond(i >= 0,  i + 1, "Negative"))
val k2: Validator[Int] = Kleisli(i => Either.cond(i%2 ==  0,  i + 1, "Odd"))
val k3: Validator[Int] = Kleisli(i => Either.cond(i < 100,  i + 1, "Greater than 100"))

val all = List(k1,k2,k3)


def f(i: Int): Valid[Int] = for {
  i1 <- k1(i)
  i2 <- k2(i1)
  i3 <- k3(i2)
} yield i3

f(123)

val kk = k1 andThen k2 andThen k3

kk(121)

val kkk = all.reduce(_ andThen _)

kkk(121)