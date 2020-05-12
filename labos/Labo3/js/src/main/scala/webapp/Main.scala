package webapp

import DSL._
import org.scalajs.dom.{html, document}
import org.scalajs.dom

object Main {

  def main(args: Array[String]): Unit = {
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    initGame()

  }

  def initGame(): Unit = {
    val snakeGridHeight: Int = 20
    val snakeGridWidth: Int = 20
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    document.body.appendChild(canvas)
    val snakeGame = new SnakeGame(snakeGridHeight, snakeGridWidth)
    snakeGame.initGame()
    val canvasy = new GridCanvasy(canvas, snakeGridHeight, snakeGridWidth, 40)
    snakeGame.board.map(x => canvasy += x.map(y => y.getSquare()))
    canvasy.initRender()
    val loop = new Loop(0)
    UserInputs.initInputsListener()
    loop.start(snakeGame.update, () => canvasy.draw())
    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

}