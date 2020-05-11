package DSL

import DSL._

object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object MovementHandler extends Settings {

  def updateDirection(lastDirection: Direction.Value): Direction.Value = {

   val direction =
      if (UserInputs.holdLeftKey && (lastDirection != Direction.Right)) Direction.Left
      else if (UserInputs.holdRightKey && (lastDirection != Direction.Left))
        Direction.Right
      else if (UserInputs.holdUpKey && (lastDirection != Direction.Down)) Direction.Up
      else if (UserInputs.holdDownKey && (lastDirection != Direction.Up))
        Direction.Down
      else lastDirection

    direction
  }
  def getWidthVariation(direction: Direction.Value): Int = {
      val modifWidth: Int =
        if (direction == Direction.Right) 1
        else if (direction == Direction.Left) -1
        else 0
    modifWidth
  }
  def getHeightVariation(direction: Direction.Value): Int = {
    val modifHeight: Int =
      if (direction == Direction.Up) -1
      else if (direction == Direction.Down) 1
      else 0
    modifHeight
  }
}