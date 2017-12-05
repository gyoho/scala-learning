//https://www.theguardian.com/info/developer-blog/2016/dec/22/parental-advisory-implicit-content

final case class Person(firstName: String, lastName: String)
final case class Dog(name: String)

object PersonCanChat {
  def chat(x: Person) = s"Hi, I'm ${x.firstName}"
}

// no property -> object makes more sense
class PPP {
  def chat(x: Person) = s"Hi, I'm ${x.firstName}"
}

PersonCanChat.chat(Person("John", "Smith"))
new PPP().chat(Person("John", "Smith"))

object DogCanChat {
  def chat(x: Dog) = s"Woof, my name is ${x.name}"
}

DogCanChat.chat(Dog("Meg"))


/** ---- Type Class ---- **/
// a range of types can share common functionality

trait CanChat[A] {
  def chat(x: A): String
}

object PersonReallyCanChat extends CanChat[Person] {
  override def chat(x: Person) = s"Hi, I'm ${x.firstName}"
}

object DogCanReallyChat extends CanChat[Dog] {
  override def chat(x: Dog) = s"Woof, my name is ${x.name}"
}

// now parameter 'x' is type safe --> accepts anything can chat
object ChatUtil {
  def chat[A](x: A, chattyThing: CanChat[A]) = {
    chattyThing.chat(x)
  }
}

ChatUtil.chat(Person("John", "Smith"), PersonReallyCanChat)
ChatUtil.chat(Dog("Meg"), DogCanReallyChat)


// even define multiple implementations for a particular type
object PersonCanReallyChatFormally extends CanChat[Person] {
  def chat(x: Person) = s"Hello, I'm ${x.firstName} ${x.lastName}"
}

ChatUtil.chat(Person("John", "Smith"), PersonCanReallyChatFormally)


/** ---- Plus Implicit Parameter ---- **/
// avoid repeating passing type class instance

object ChatUtilImpl {
  def chat[A](x: A)(implicit chattyThing: CanChat[A]) = {
    chattyThing.chat(x)
  }
}

// bundle type class instance in companion object
object CanChat {
  implicit object PersonCanChat extends CanChat[Person] {
    def chat(x: Person) = s"Hi, I'm ${x.firstName}"
  }
  implicit object DogCanChat extends CanChat[Dog] {
    def chat(x: Dog) = s"Woof, my name is ${x.name}"
  }
}

// ...in another package
import CanChat._

ChatUtilImpl.chat(Person("John", "Smith"))
ChatUtilImpl.chat(Dog("Meg"))


/** ---- Plus Implicit Class ---- **/
// automatically convert instances into instances of ChatUtil by trying to call the "chat" method

object ChattyAddons {
  implicit object PersonCanChat extends CanChat[Person] {
    def chat(x: Person) = s"Hi, I'm ${x.firstName}"
  }
  implicit object DogCanChat extends CanChat[Dog] {
    def chat(x: Dog) = s"Woof, my name is ${x.name}"
  }
  implicit class ChatUtil[A](x: A) {
    def chat(implicit makesChatty: CanChat[A]) = {
      makesChatty.chat(x)
    }
  }
}


/** ---- Polymorphism Failure ---- **/

trait CC2 {
  def chat(): String
}


// cannot extend final class
//class P2(firstName: String, lastName: String) extends Person(firstName, lastName) with CC2 {
//  override def chat() = s"Hi, I'm $firstName"
//}

// what's the type of the parameter
//object PP extends CC2 {
//  def chat() = s"Hi, I'm ${...}"
//}

// Cannot extend --> accessing properties or calling methods need instance
// --> Adaptor pattern (i.e. passing an instance as a parameter)
// --> But what's the type of the parameter now?
// --> possibly use [Any] as the parameter type e.g) def chat(x: Any): String
// --> but too generic
// --> Use Type Class (i.e. trait with type!!)



/** ---- Pattern Matching Failure ---- **/


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

// -----------------------------------------------------


trait Equal[A] {
  def equal(left: A, right: A): Boolean
}

case class Prsn(name: String, email: String)

object EmailEqual extends Equal[Prsn] {
  def equal(left: Prsn, right: Prsn): Boolean = left.email == right.email
}

object PrsnEqual extends Equal[Prsn] {
  def equal(left: Prsn, right: Prsn): Boolean = EmailEqual.equal(left, right) && left.name == right.name
}

