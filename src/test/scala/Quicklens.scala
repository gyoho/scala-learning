import com.softwaremill.quicklens._
import org.scalatest.{Matchers, WordSpec}

class Quicklens extends WordSpec with Matchers {

  "quicklens" should {
    "copy an object" in {
      case class Street(name: String)
      case class Address(street: Street)
      case class Person(address: Address)

      val person = Person(Address(Street("1 Functional Rd.")))

      val personCopy = person.copy(
        address = person.address.copy(
          street = person.address.street.copy(
            name = person.address.street.name.toUpperCase
          )
        )
      )

      val personModify = person.modify(_.address.street.name).usingIf(1 > 0)(_.toUpperCase)

      personModify should be(personCopy)
    }
  }
}
