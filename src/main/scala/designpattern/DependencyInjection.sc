import scala.util.Try

case class User()

trait Repository {
  def save(user: User): Try[String]
}

trait DatabaseRepository extends Repository {
  override def save(user: User): Try[String] = {
    println("doing db transaction")
    Try("Success")
  }
}

trait UserService { self: Repository => // requires Repository
  def create(user: User): Try[String] = {
    // ...
    save(user)
  }
}

new UserService with DatabaseRepository // cake pattern