val subjects = List("Noel", "The cat", "The dog")
val commonVerbs = List("chased", "slept on")
val commonObjects = List("the book", "the ball", "the bed")

val sentences = for {
  sub <- subjects
  verb <- commonVerbs
  obj <- commonObjects
} yield s"$sub $verb $obj"

def verbsFor(subject: String): List[String] =
  subject match {
    case "Noel" => "wrote" :: commonVerbs
    case "The cat" => "meowed at" :: commonVerbs
    case "The dog" => "barked at" :: commonVerbs
  }

def objectsFor(verb: String): List[String] =
  verb match {
    case "wrote" => List("the book", "the letter", "the code")
    case "chased" => List("the ball", "the dog", "the cat")
    case "slept on" => List("the bed", "the mat", "the train")
    case "meowed at" => List("Noel", "the door", "the food cupboard")
    case "barked at" => List("the postman", "the car", "the cat")
  }

val formalSentences = for {
  sub <- subjects
  verb <- verbsFor(sub)
  obj <- objectsFor(verb)
} yield s"$sub $verb $obj"


final class Distribution[T](val events: List[(T, Double)])

object Distribution {
  def uniform[T](list: List[T]): Distribution[T] = {
    val prob = 1.0 / list.length
    new Distribution[T](list.map(_ -> prob))
  }
}

