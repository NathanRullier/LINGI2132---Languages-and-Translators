package DSL

import DSL._

case class Snake(head: Square, tail: Seq[Square]) {
  val completeSnake = head +: tail
  //println("snake: " +completeSnake)
}