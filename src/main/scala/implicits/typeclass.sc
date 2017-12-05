sealed trait Json

object Json{
  case class Str(s: String) extends Json
  case class Num(value: Double) extends Json
  // ... many more definitions
}


def convertToJsonPtttr(x: Any): Json = {
  x match{
    case s: String => Json.Str(s)
    case d: Double => Json.Num(d)
    case i: Int => Json.Num(i.toDouble)
    // maybe more cases for float, short, etc.
  }
}


//convertToJsonPtttr('a')
//--> runtime error b/c Any


/** ---- Type Class w/ Implicit ---- **/


trait Jsonable[T]{
  def serialize(t: T): Json
}

object Jsonable{
  implicit object StringJsonable extends Jsonable[String]{
    def serialize(t: String) = Json.Str(t)
  }
  implicit object DoubleJsonable extends Jsonable[Double]{
    def serialize(t: Double) = Json.Num(t)
  }
  implicit object IntJsonable extends Jsonable[Int]{
    def serialize(t: Int) = Json.Num(t.toDouble)
  }
}


def convertToJsonImplicit[T](x: T)(implicit converter: Jsonable[T]): Json = {
  converter.serialize(x)
}


//convertToJsonImplicit('a')
//--> compilation error

