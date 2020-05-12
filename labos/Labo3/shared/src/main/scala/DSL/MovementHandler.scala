package DSL

import DSL._

object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object MovementHandler extends Settings {

  var direction: Direction.Value = Direction.Right

  def updateDirection(): Unit = {
    UserInputs.onLeftKeyPressed = () => {
      if(direction != Direction.Right) direction = Direction.Left
    }
    UserInputs.onRightKeyPressed = () => {
      if(direction != Direction.Left) direction = Direction.Right
    }
    UserInputs.onUpKeyPressed = () => {
      if(direction != Direction.Down) direction = Direction.Up
    }
    UserInputs.onDownKeyPressed = () => {
      if(direction != Direction.Up) direction = Direction.Down
    }
    UserInputs.initInputsListener2()
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