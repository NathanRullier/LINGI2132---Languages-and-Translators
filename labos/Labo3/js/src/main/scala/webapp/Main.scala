package webapp

import DSL._
import org.scalajs.dom.{html, document}
import org.scalajs.dom

object Main {

  def main(args: Array[String]): Unit = {
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    initGame(canvas)


  }

  def initGame(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    document.body.appendChild(canvas)
    val canvasy = new Canvasy(canvas)
    val game = new Game()
    val loop = new Loop()
    //threadLoop.start(game.update, () => canvasy.renderHead())

    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

}