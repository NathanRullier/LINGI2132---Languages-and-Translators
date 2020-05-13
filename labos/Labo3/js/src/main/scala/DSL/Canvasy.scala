package DSL

import DSL._
import org.scalajs.dom.html
import scala.scalajs.js
import org.scalajs.dom

class Canvasy(canvas: html.Canvas, height: Int, width: Int) {

  private var shapes = Array[Shape]()

  private val ctx: dom.CanvasRenderingContext2D =
    canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def draw(): Unit = {
    shapes.map(
        x =>
            x match {
              case square: Square => draw(square)
              case composedShape: ComposedShape[Shape] => draw(composedShape)
              case shape: Shape => draw(shape)
            }

    )
  }

  def getShapes(): Array[Shape] ={
    shapes
  }

  def draw(s: Shape): Unit = {}

  def draw(square: Square): Unit = {
    val style = square.color
    ctx.fillStyle = style
    ctx.fillRect(square.x, square.y, square.side, square.side)
    ctx.stroke()
  }

  def draw(composedShapes: ComposedShape[Shape]): Unit = {
    composedShapes.foreach(
      x =>
        x match {
          case square: Square => draw(square)
          case shape: Shape => draw(shape)
          case composedShape: ComposedShape[Shape] => draw(composedShape)
        }

    )
  }

  def +=(circles: Array[Circle]) {
    shapes = shapes ++ circles
  }

  def +=(rectangles: Array[Rectangle]) {
    shapes = shapes ++ rectangles
  }

  def +=(squares: Array[Square]) {
    shapes = shapes ++ squares
  }

  def +=(composedShapes: Array[ComposedShape[Square]]): Unit = {
    shapes = shapes ++ composedShapes

  }

  def initRender() = {
    canvas.width = width
    canvas.height = height
    draw()
  }

  def resetGame(): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.clearRect(0, 0, width, height)
  }
}
