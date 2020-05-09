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
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    document.body.appendChild(canvas)
    val canvasy = new Canvasy(canvas)
    val game = new Game(canvasy)
    val loop = new Loop()
    val head = new Square(0,0,0)
    UserInputs.initInputsListener()
    loop.start(game.update, () => canvasy.fkall())

    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

}