val s1 = (1 to 100000000).toStream

var inc = 0

val s2 = s1.map { num =>
  inc = num
  num
}

inc

s2.take(3).foreach(println)

inc

s2.take(100).foreach(println)

inc