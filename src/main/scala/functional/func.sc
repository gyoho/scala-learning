

case class Email(subject: String,
                 text: String,
                 sender: String,
                 recipient: String)

type EmailFilter = Email => Boolean


def newMailsForUser(mails: Seq[Email], f: EmailFilter) = mails.filter(f)

def blah(x: Int)(y: Long) = x + y
val b = blah(15)_
b(2L)


// different from val definition?

val sentByOneOf: Set[String] => Email => Boolean = senders => email => senders.contains(email.sender)
def sentByOneOfFunc(senders: Set[String]): EmailFilter = email => senders.contains(email.sender)


val minimumSize: Int => EmailFilter = n => email => email.text.length >= n
def minuminSizeFunc(n: Int): EmailFilter = email => email.text.length >= n

val emailFilter2: EmailFilter = minuminSizeFunc(5)




val emailFilter0: EmailFilter = sentByOneOf(Set("johndoe@example.com"))
val emailFilter1: EmailFilter = minimumSize(5)


val mails = Email(
  subject = "It's me again, your stalker friend!",
  text = "Hello my friend! How are you?",
  sender = "johndoe@example.com",
  recipient = "me@example.com") :: Nil

val m = newMailsForUser(mails, emailFilter2)


"hello".drop(1)


// --- Reusing existing functions ---

type SizeChecker = Int => Boolean
val sizeConstraint: SizeChecker => EmailFilter = f => email => f(email.text.length)


def complement[A](predicate: A => Boolean) = (a: A) => !predicate(a)
val notSentByAnyOfOptimized = sentByOneOf andThen(g => complement(g))


2 :: List(1)
List(1).::(2)
List(1,2,3) :+ 4


Seq(1,2,3) :+ 4
4 +: Seq(1,2,3)


def any[A](predicates: (A => Boolean)*): A => Boolean = a => predicates.exists(pred => pred(a))

