// brand = {Nike, Adidas, Puma}
// ...
// color = {Black, white, Red}
// type = {Running, Soccer}

// Output:
// Nike, Black, Running
// Nike, Black, Soccer
// ...
// Nike, White, Running
// ...
// Puma, Red, Soccer

def combinationsSeq[Attribute](attributes: Seq[Seq[Attribute]]): Seq[Seq[Attribute]] = {
  attributes match {
    case Seq() =>
      Nil
    case Seq(attribute1) =>
      attribute1.map(Seq(_))
    case Seq(head, tail @ _*) =>
      head.flatMap { attribute0 =>
        combinationsSeq(tail).map { res =>
          attribute0 +: res
        }
      }
  }
}

def combinationsList[Attribute](attributes: Seq[List[Attribute]]): List[List[Attribute]] = {
  attributes match {
    case List() =>
      List(Nil)
    case List(head, tail @ _*) =>
      head.flatMap { attribute0 =>
        combinationsList(tail).map {
          attribute0 :: _
        }
      }
  }
}

def combinationsMemoization[Attribute](attributes: Seq[Seq[Attribute]]): Seq[List[Attribute]] = {

  var cache: Map[Seq[Seq[Attribute]], Seq[List[Attribute]]] = Map.empty

  def loop(attributes: Seq[Seq[Attribute]]): Seq[List[Attribute]] = {
    cache.get(attributes) match {
      case Some(c) => c
      case None =>
        val res: Seq[List[Attribute]] = attributes match {
          case Seq() =>
            Seq(Nil)
          case Seq(head, tail@_*) =>
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


def combinationsStream[Attribute](attributes: Stream[Stream[Attribute]]): Stream[List[Attribute]] = {
  attributes match {
    case Stream.Empty =>
      Stream(Nil)
    case head #:: tail => // faster than Seq(head, tail @ _*)
      head.flatMap { attribute0 =>
        combinationsStream(tail).map {
          attribute0 :: _
        }
      }
  }
}

def doMatch(seq: Seq[Int]): Unit = seq match {
  case h :: t =>
  case _ =>
}

def doMatch2(seq: Seq[Int]): Unit = seq match {
  case Seq(x) =>
  case Seq(x, xs @ _*) =>
}


val result = combinationsList(List(List("Nike", "Puma"), List("Black", "Red"), List("Running", "Soccer")))

assert(result.contains(Seq("Nike", "Black", "Running")))
assert(result.contains(Seq("Nike", "Red", "Running")))
assert(result.contains(Seq("Puma", "Red", "Soccer")))

assert(combinationsList(List(List("Nike", "Puma"))) == List(List("Nike"), List("Puma")))

//failed case
assert(combinationsList(Nil) == Seq(Seq.empty))
