object Command {
  var history: Seq[() => Unit] = Seq.empty

  def invoke(command: => Unit): Unit = {
    command
    history :+= command _
  }
}

Command.invoke(println("foo"))

Command.invoke {
  println("bar")
  println("dal")
}

println(Command.history)