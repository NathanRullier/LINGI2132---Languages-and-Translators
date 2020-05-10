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
    document.body.appendChild(canvas)
    val canvasy = new Canvasy(canvas)
    canvasy.initRender()
    val game = new Game(canvasy)
    game.initGame()
    val loop = new Loop()
    UserInputs.initInputsListener()
    loop.start(game.update, () => canvasy.draw())

    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

}