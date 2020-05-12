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

    var snake = ComposedShape(List.fill(30)(new Square(0, 0, pixelSize)))
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
      val head = new Square(snake(0).x, snake(0).y, snake(0).side)
      head change Color("blue")
      direction match {
        case Direction.Left => head moveX -pixelSize
        case Direction.Right => head moveX pixelSize
        case Direction.Up => head moveY -pixelSize
        case Direction.Down => head moveY pixelSize
      }
      snake.addFirst(head)
      snake.removeLast()
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