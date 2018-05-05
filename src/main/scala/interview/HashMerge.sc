import scala.util.{Failure, Success, Try}

val users: Map[Int, String] = Map(
  1 -> "Jason",
  2 -> "Robert",
  3 -> "Naomi",
  4 -> "Angel"
)

val incomes: Seq[(Int, Double)] = Seq(
  (1, 1.0),
  (2, 3.2),
  (4, 2.0),
  (1, 4.0),
  (2, 6.8)
)

val l = List(1,2,3,4,5)
l.sum

incomes.groupBy(_._1).mapValues(seq => seq.map(_._2).sum)
incomes.groupBy(_._1).collect {
  case e => e._1 -> e._2.map(_._2).sum
}.toList

def userWithLowIncome(users: Map[Int, String], incomes: Seq[(Int, Double)], threshold: Double): Seq[String] = {
  val aggrIncome: Map[Int, Double] = incomes.groupBy(income => income._1).mapValues(ics => ics.foldLeft(0.0)(_ + _._2))
  val userIdsWithLowIncome: Set[Int] = aggrIncome.filter(x => x._2 < threshold).keySet
  users.filter(user => !aggrIncome.contains(user._1) || userIdsWithLowIncome.contains(user._1)).values.toSeq
}

userWithLowIncome(users, incomes, 5.5)


// Link: https://stackoverflow.com/questions/41757513/aggregate-reduce-by-key-function-for-a-map-in-scala
val m = Map("x"-> "abc", "y"->"adc","z"->"abc", "l"-> "ert","h"->"dfg", "p"-> "adc")
m.groupBy(x => x._2)
  .mapValues(_.keySet)


//def listWithSum(numbers: List[Int]) = numbers.foldLeft((List.empty[Int], 0)) {
//  (resultingTuple, currentInteger) =>
//    (currentInteger :: resultingTuple._1, currentInteger + resultingTuple._2)
//}

def listWithSum(numbers: List[Int]): (List[Int], Int) = numbers.foldLeft((List.empty[Int], 0)) {
  case ((list, total), currentInteger) =>
    (list :+ currentInteger, currentInteger + total)
}

listWithSum(List(1,2,3,4,5))

val l1 = 1 :: 2 :: 3 :: Nil
val l2 = List.empty[Int] :+ 1 :+ 2
val l3 = 1 :: 2 :: List.empty[Int]

l1 ::: l2

//def countRecords[T](results: List[Try[T]]): (Int, Int) = results.foldLeft((0, 0)) {
//  (acc, res) =>
//    if (res.isSuccess) (acc._1 + 1, acc._2) else (acc._1, acc._2 + 1)
//}

def countRecords[T](results: List[Try[T]]): (Int, Int) = results.foldLeft((0, 0)) {
  case ((s, f), res) =>
    if (res.isSuccess) (s + 1, f) else (s, f + 1)
}

countRecords(List(Success("foo"), Failure(new Exception("boo"))))

Map("first"->1, "second"->2).foldLeft(0){ case (a, (k, v)) => a+v }
