sealed trait TrafficLight {
  def next: TrafficLight = {
    this match {
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }
}
final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Yellow extends TrafficLight

Red.next
Green.next
Yellow.next
