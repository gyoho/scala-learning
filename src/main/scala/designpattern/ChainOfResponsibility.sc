case class Event(source: String)

type EventHandler = PartialFunction[Event, String]

val defaultHandler: EventHandler = PartialFunction(_ => "defaultHandler")

val keyboardHandler: EventHandler = {
  case Event("keyboard") => "keyboardHandler"
}

def mouseHandler(delay: Int): EventHandler = {
  case Event("mouse") => s"mouseHandler with $delay delay"
}

val handler: EventHandler = keyboardHandler.orElse(mouseHandler(100)).orElse(defaultHandler)

val events = List(Event("keyboard"), Event("mouse"), Event("random"))

handler.apply(Event("foo"))

events map handler