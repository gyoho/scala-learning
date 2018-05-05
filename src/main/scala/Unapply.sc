trait User {
  def name: String
  def score: Int
}
class FreeUser(val name: String, val score: Int, val upgradeProbability: Double)
  extends User
class PremiumUser(val name: String, val score: Int) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int, Double)] =
    Some((user.name, user.score, user.upgradeProbability))
}
object PremiumUser {
  def unapply(user: PremiumUser): Option[(String, Int)] = Some((user.name, user.score))
}

object PremiumCandidate {
  def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.75
}

val user: User = new FreeUser("Daniel", 3000, 0.7d)

user match {
  case PremiumUser(name, _) => "Welcome back, dear " + name
  case _ => "boo"
}