package interview

object Combinations extends App {

  def combinations[Attribute](attributes: Seq[Seq[Attribute]]): Seq[List[Attribute]] = {
    attributes match {
      case Seq() =>
        println("---------yo---------")
        println(s"attributes0: $attributes")
        Seq(Nil)
      case Seq(head, tail@_*) =>
        println("---------ho---------")
        println(s"attributes1: $attributes")
        println(s"head: $head")
        println(s"tail: $tail")
        head.flatMap { attribute0 =>
          combinations(tail).map {
            attribute0 :: _
          }
        }
    }
  }

  //  val res = combinations(List(List("Nike", "Puma")))
  val res = combinations(List(List("Nike", "Puma"), List("Black")))

  println("---------res---------")
  println(res)
  print("\n\n\n")

  def combinationsOpt[Attribute](attributes: Seq[Seq[Attribute]]): Seq[List[Attribute]] = {

    var cache: Map[Seq[Seq[Attribute]], Seq[List[Attribute]]] = Map.empty

    def loop(attributes: Seq[Seq[Attribute]]): Seq[List[Attribute]] = {
      cache.get(attributes) match {
        case Some(c) =>
          println("---------go---------")
          println(s"got cached result: $attributes, $c")
          c
        case None =>
          val res: Seq[List[Attribute]] = attributes match {
            case Seq() =>
              println("---------yo---------")
              println(s"attributes0: $attributes")
              Seq(Nil)
            case Seq(head, tail@_*) =>
              println("---------ho---------")
              println(s"attributes1: $attributes")
              println(s"head: $head")
              println(s"tail: $tail")
              head.flatMap { attribute0 =>
                loop(tail).map {
                  attribute0 :: _
                }
              }
          }

          cache = cache + (attributes -> res)
          res
      }
    }
    loop(attributes)
  }

  //  val res = combinations(List(List("Nike", "Puma")))
  val resOpt = combinationsOpt(List(List("Nike", "Puma"), List("Black")))

  println("---------resOpt---------")
  println(resOpt)
}