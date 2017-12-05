sealed trait BinaryTree[T]

final case class Node[T](left: BinaryTree[T], right: BinaryTree[T]) extends BinaryTree[T]
final case class Leaf[T](elem: T) extends BinaryTree[T]

