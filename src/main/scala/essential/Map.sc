import scala.util.Try

val people = Set(
  "Alice",
  "Bob",
  "Charlie",
  "Derek",
  "Edith",
  "Fred"
)

val ages = Map(
  "Alice"   -> 20,
  "Bob"     -> 30,
  "Charlie" -> 50,
  "Derek"   -> 40,
  "Edith"   -> 10,
  "Fred"    -> 60
)

val favoriteColors = Map(
  "Bob"     -> "green",
  "Derek"   -> "magenta",
  "Fred"    -> "yellow"
)

val favoriteLolcats = Map(
  "Alice"   -> "Long Cat",
  "Charlie" -> "Ceiling Cat",
  "Edith"   -> "Cloud Cat"
)

def favoriteColor(name: String): String = favoriteColors.getOrElse(name, "beige")
favoriteColor("hio")

def printColors() = {
  favoriteColors.foreach{
    case (name, color) => println(s"${name} -> ${color}")
  }
}

def lookup[T](name: String, map: Map[String, T]): Option[T] = map.get(name)

val oldest: Option[String] = favoriteColors.get(Try(ages.maxBy(_._2)._1).getOrElse(""))

1 until 10 map {i => s"num=$i"}