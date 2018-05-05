// Link: https://stackoverflow.com/questions/34001558/better-way-of-compressing-a-string

/*
  Input: abcaaabbb
  Output: abca3b3
*/

/*
  Input: aabccca
  Output: a2b1c3a1
 */

def compress(str: Seq[Char]): String = {
  str.foldLeft(List.empty[(Char, Int)]) { (acc, char) =>
//    println(s"$acc, $char")
    acc match {
      case (`char`, n) :: tail => (char, n + 1) :: tail
      case _ => (char, 1) :: acc
    }
  }.reverse.map {
//    case (c, 1) => c.toString
    case (c, n) => s"$c$n"
  }.mkString
}

val input = "aabccca".toCharArray.toSeq
val output = compress(input)

// Java solution: https://gist.github.com/gyoho/29d734f18415ec63bc6f


('b', 0) :: ('a', 2) :: List.empty[(Char, Int)]
List.empty[(Char, Int)] :+ ('a', 2)


val l1 = 1 :: 2 :: 3 :: Nil
val l2 = List.empty[Int] :+ 1 :+ 2
l1 ::: l2


(1 to 1000000000).view.filter(_ % 2 == 0).take(10).toList
(1 to 1000000000).toStream.filter(_ % 2 == 0).take(10).toList