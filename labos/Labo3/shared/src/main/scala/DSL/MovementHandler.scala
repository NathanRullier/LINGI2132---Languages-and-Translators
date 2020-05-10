package DSL

import DSL._

object MovementHandler extends Settings {

  def updateSnakeDirection(lastDirection: Direction.Value): Direction.Value = {

   val direction =
      if (UserInputs.holdLeft && (lastDirection != Direction.Right)) Direction.Left
      else if (UserInputs.holdRight && (lastDirection != Direction.Left))
        Direction.Right
      else if (UserInputs.holdUp && (lastDirection != Direction.Down)) Direction.Up
      else if (UserInputs.holdDown && (lastDirection != Direction.Up))
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