
/** ---- Something missing: implicit parameters ---- **/
// Scala compiler looks for the missing parameter in the current scope
// hunting for a value declared with the keyword “implicit” and with the expected "type"

// implicit parameter is allowed only for the final parameter (like varargs)
def multiply(x: Int)(implicit y: Int) = x * y

multiply(3)(10) // 30
multiply(4)(10) // 40

multiply(3)
// error: could not find implicit value for parameter factor: Int

implicit val y: Int = 10

multiply(3) // 30
multiply(4) // 40

//implicit val x: Int = 11
// the implicit value must resolve to a single value to avoid conflict

multiply(3)


/** ---- Something mismatched: implicit conversions ---- **/
// 1) Scala compiler convert types when there is a "type mismatch"

def alert(msg: String): Unit = println(msg)

alert(7)
// error: type mismatch;
// found   : Int(7)
// required: String

implicit def intToString(i: Int): String = i.toString

alert(7) // equivalent to: alert(intToString(7))
// 7


// 2) Scala compiler convert types when access an object member not defined for that type
3.chat
// error: value chat is not a member of Int

class LoquaciousInt(x: Int) {
  def chat(): Unit = for(_ <- 1 to x) println("Hi!")
}

implicit def intToLoquaciousInt(x: Int): LoquaciousInt = new LoquaciousInt(x)

3.chat  // equivalent to: intToLoquaciousInt(3).chat()
// Hi!
// Hi!
// Hi!

/** ---- Implicit Class: type enrichment (part of implicit conversions) ---- **/
// add extra functionality to classes which may be defined in libraries
// whose source code we cannot modify

// converting to a new type which we have defined ourselves
// If you have a class whose constructor takes a single argument
// marking the class as implicit makes compiler automatically do implicit conversions
// from the type of its constructor argument to the type of the class

implicit class LoquaciousIntShorthand(x: Int) {
  def chat(): Unit = for(_ <- 1 to x) println("Hi!")
}

3.chat  // equivalent to: new LoquaciousIntShorthand(3).chat
