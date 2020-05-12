package webapp

import DSL._
import org.scalajs.dom.{html, document}
import org.scalajs.dom

object Main {

  def main(args: Array[String]): Unit = {
    initGame()

  }

  def initGame(): Unit = {
    val snakeGridHeight: Int = 20
    val snakeGridWidth: Int = 20
    val pixelSize = 40
    var GameSpeed = 8

    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    document.body.appendChild(canvas)
    val canvasy = new GridCanvasy(canvas, snakeGridHeight, snakeGridWidth, pixelSize)
    canvasy.initRender()
    //val snakeGame = new SnakeGame(snakeGridHeight, snakeGridWidth)
    //snakeGame.initGame()
    val background = new Square(0, 0, pixelSize * snakeGridWidth)
    background change Color("black")
    canvasy += Array(background)

    val snake = new Square(0, 0, pixelSize)
    snake change Color("blue")
    canvasy += Array(snake)

    var direction = Direction.Right
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

    val movement = () => {
      direction match {
        case Direction.Left => snake moveX -pixelSize
        case Direction.Right => snake moveX pixelSize
        case Direction.Up => snake moveY -pixelSize
        case Direction.Down => snake moveY pixelSize
      }
    }

    //snakeGame.board.map(x => canvasy += x.map(y => y.getSquare()))
    //canvasy.initRender()
    val loop = new Loop(0)
    //loop.start(snakeGame.update, () => canvasy.draw())
    var compteur = 0
    loop.start(() => {
      compteur += 1
      if (compteur % GameSpeed == 0) {
        movement()
      }
    }, () => canvasy.draw())
    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

}