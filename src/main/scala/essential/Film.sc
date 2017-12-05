
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

def directorsOfMoreFilmProduced(numberOfFilms: Int): Seq[Director] = directors.filter(d => d.films.size > numberOfFilms)
assert(directorsOfMoreFilmProduced(3).size == 2)

def directorsBornBefore(year: Int): Option[Director] = directors.find(d => d.yearOfBirth < year)

def directorsBornBeforeAndMoreFilmProduced(numberOfFilms: Int, year: Int) = directors.filter(d => d.yearOfBirth < year && d.films.size > numberOfFilms)
def directorsBornBeforeAndMoreFilmProducedChain(numberOfFilms: Int, year: Int) = directors.filter(_.yearOfBirth < year).filter(_.films.size > numberOfFilms)

val year = 1960
val numberOfFilms = 3

directorsBornBeforeAndMoreFilmProduced(numberOfFilms, year)
directorsBornBeforeAndMoreFilmProducedChain(numberOfFilms, year)

assert(directorsBornBeforeAndMoreFilmProduced(numberOfFilms, year) == directorsBornBeforeAndMoreFilmProducedChain(numberOfFilms, year))


def sortWithAge(ascending: Boolean): Seq[Director] = directors.sortWith(if(ascending) _.yearOfBirth > _.yearOfBirth else _.yearOfBirth < _.yearOfBirth)

sortWithAge(true).foreach(println)