import cats.implicits._

case class MyOdxError(err: String)
case class MyAction(num: Int)
case class MyAccess(`type`: String, name: String, MyActions: Seq[MyAction])

object MyAction {
  def fromString(str: String): Either[MyOdxError, MyAction] = str match {
    case "pull" | "push" => Right(new MyAction(1))
    case _ => Left(MyOdxError("FOO!!"))
  }
}

object MyAccess {
  def apply(scopeStr: String): Either[MyOdxError, MyAccess] = {
    scopeStr.split(':').toList match {
      case typ :: name :: actionStr :: Nil =>
        val MyActions = actionStr.split(',').toSeq
        val MyActionsEither = MyActions.map(MyAction.fromString).sequence
        MyActionsEither.map(MyActions => new MyAccess(typ, name, MyActions))
      case _ => Left(MyOdxError("Boom!!"))
    }
  }

  def scopeToMyAccesses(scope: String): Either[MyOdxError, Seq[MyAccess]] = {
    val scpSeq = scope.split(' ').toSeq
    scpSeq.map(MyAccess(_)).sequence
  }
}

/**
 * Thread all the G effects through the F structure to invert the
 * structure from F[G[A]] to G[F[A]].
 *
 * Example:
 * {{{
 * scala> import cats.implicits._
 * scala> val x: List[Option[Int]] = List(Some(1), Some(2))
 * scala> val y: List[Option[Int]] = List(None, Some(2))
 * scala> x.sequence
 * res0: Option[List[Int]] = Some(List(1, 2))
 * scala> y.sequence
 * res1: Option[List[Int]] = None
 * }}}
 */
def traverse[A, B](eithers: List[Either[A, B]]): Either[A, List[B]] = {
  val builder = List.newBuilder[B]
  val it = eithers.iterator

  while (it.hasNext) it.next() match {
    case Left(a) => return Left(a) // need return to short circuit
    case Right(b) => builder += b
  }

  Right(builder.result())
}

traverse(List(Right(1), Right(2), Left("Error"), Right(3)))
traverse(List(Right(1), Right(2), Right(3)))