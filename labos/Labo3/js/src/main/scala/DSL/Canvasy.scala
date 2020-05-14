package DSL

import DSL._
import org.scalajs.dom.html
import scala.scalajs.js
import org.scalajs.dom

/**
 * Class that handles everything related to the canvas and the view
 * @param canvas Html canvas
 * @param height game max height
 * @param width game max width
 */
class Canvasy(canvas: html.Canvas, canvasMaxHeight: Int, canvasMaxWidth: Int) {

  private var shapes = Array[Shape]()

  private val ctx: dom.CanvasRenderingContext2D =
    canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  /**
   * Draw function that draws every shape on the canvas
   */
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

  /**
   * getter of the shapes in the canvas
   * @return Array of shapes in the canvas
   */
  def getShapes(): Array[Shape] ={
    shapes
  }

  def draw(s: Shape): Unit = {}

  /**
   * Function that draws a square on the canvas
   * @param square Square drawn
   */
  def draw(square: Square): Unit = {
    val style = square.color
    ctx.fillStyle = style
    ctx.fillRect(square.x, square.y, square.side, square.side)
    ctx.stroke()
  }

  /**
   * Function that draws a every shape of a composed shape depending on their type
   * @param composedShapes
   */
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

  /**
   * Add an array of cricles to the array of shapes in the canvas
   * @param circles Array of circles to add
   */
  def +=(circles: Array[Circle]) {
    shapes = shapes ++ circles
  }

  /**
   * Add an array of rectangles to the array of shapes in the canvas
   * @param rectangles Array of rectangles to add
   */
  def +=(rectangles: Array[Rectangle]) {
    shapes = shapes ++ rectangles
  }

  /**
   * Add an array of squares to the array of shapes in the canvas
   * @param squares Array of squares to add
   */
  def +=(squares: Array[Square]) {
    shapes = shapes ++ squares
  }

  /**
   * Add an array of composed shapes to the array of shapes in the canvas
   * @param composedShapes the composed shape to add
   */
  def +=(composedShapes: Array[ComposedShape[Square]]): Unit = {
    shapes = shapes ++ composedShapes

  }

  /**
   * initiation of the canvas with his width and height
   */
  def initRender() = {
    canvas.width = canvasMaxWidth
    canvas.height = canvasMaxHeight
    draw()
  }

  /**
   * reset of the game render
   */
  def resetGame(): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.clearRect(0, 0, width, height)
  }
}
