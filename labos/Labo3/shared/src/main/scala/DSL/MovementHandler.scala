package DSL

import DSL._

object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object MovementHandler {

  var direction: Direction.Value = Direction.Right

  def updateDirection(lastDirection: Direction.Value): Unit = {
    UserInputs.onLeftKeyPressed = () => {
      direction = Direction.Left
    }
    UserInputs.onRightKeyPressed = () => {
      direction = Direction.Right
    }
    UserInputs.onUpKeyPressed = () => {
      direction = Direction.Up
    }
    UserInputs.onDownKeyPressed = () => {
      direction = Direction.Down
    }
    UserInputs.initInputsListener()
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