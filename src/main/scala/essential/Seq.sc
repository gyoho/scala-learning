case class Film(
    name: String,
    yearOfRelease: Int,
    imdbRating: Double
)

case class Director(
    firstName: String,
    lastName: String,
    yearOfBirth: Int,
    films: Seq[Film]
)

val memento = Film("Memento", 2000, 8.5)
val darkKnight = Film("Dark Knight", 2008, 9.0)
val inception = Film("Inception", 2010, 8.8)
val highPlainsDrifter = Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales = Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven = Film("Unforgiven", 1992, 8.3)
val granTorino = Film("Gran Torino", 2008, 8.2)
val invictus = Film("Invictus", 2009, 7.4)
val predator = Film("Predator", 1987, 7.9)
val dieHard  = Film("Die Hard", 1988, 8.3)
val huntForRedOctober = Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair = Film("The Thomas Crown Affair", 1999, 6.8)


val eastwood = Director("Clint", "Eastwood", 1930, Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
val mcTiernan = Director("John", "McTiernan", 1951, Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
val nolan = Director("Christopher", "Nolan", 1970, Seq(memento, darkKnight, inception))
val someGuy = Director("Just", "Some Guy", 1990, Seq())

val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

//Starting with the defini on of nolan, create a list containing the names of the films directed by Christopher Nolan
nolan.films.map(_.name)

//Starting with the defini on of directors, create a list containing the names of all films by all directors
directors.flatMap(_.films.map(_.name))

//Starting with mcTiernan, find the date of the earliest McTiernan film.
mcTiernan.films.sortWith(_.yearOfRelease < _.yearOfRelease).head
mcTiernan.films.foldLeft(Int.MaxValue) {
  (current, film) => math.min(current, film.yearOfRelease)
}

//Starting with directors, find all films sorted by descending IMDB ra ng:
directors.flatMap(_.films).sortWith(_.imdbRating > _.imdbRating)

//Starting with directors again, find the average score across all films:
val films = directors.flatMap(director => director.films)
films.foldLeft(0.0)(_ + _.imdbRating) / films.size

//Starting with directors, print the following for every film: "Tonight only! FILM NAME by DIRECTOR!"
directors.flatMap {
  d => d.films.map(f => s"Tonight only! ${f.name} by ${d.firstName} ${d.lastName}!")
}.foreach(println)

directors.foreach { d =>
  d.films.foreach { f =>
    println(s"Tonight only! ${f.name} by ${d.firstName} ${d.lastName}!") }
}

//Starting with directors again, find the earliest film by any director
directors.flatMap(_.films).sortWith(_.yearOfRelease < _.yearOfRelease).head




//Write a method to find the smallest element of a Seq[Int]
def min(seq: Seq[Int]): Int = seq.foldLeft(Int.MaxValue)(math.min)

//Unique number
val list = Seq(1, 1, 2, 4, 3, 4)
list.filter { x => list.count(_ == x) == 1 }
list.distinct
list.reverse






