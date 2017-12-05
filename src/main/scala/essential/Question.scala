package main.scala.essential

/**
  * Question: case class vs case object. why compilation error
  */

sealed trait IntList
case object End extends IntList
case class Pair(head: Int, tail: IntList) extends IntList


//sealed trait LinkedList[A]
//case class End[A]() extends LinkedList[A]
//case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A] 


//sealed trait LinkedList[T]
//case object End[T] extends LinkedList[T]
//case class Pair[T](head: T, tail: LinkedList[T]) extends LinkedList[T]