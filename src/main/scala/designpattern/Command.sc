import scala.util.Try

object Command {
  var history: Seq[() => Any] = Seq.empty

  def invoke[A](command: => A): Try[A] = {
    history :+= command _
    Try(command)
  }
}

Command.invoke("foo")
Command.invoke(3)

println(Command.history)