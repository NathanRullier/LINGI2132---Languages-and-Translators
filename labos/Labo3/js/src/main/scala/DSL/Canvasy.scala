package DSL

import DSL._
import javax.swing.Timer
import org.scalajs.dom.html
import scala.scalajs.js
import js.Dynamic.{ global => g }
import org.scalajs.dom

class Canvasy(canvas: html.Canvas) extends Settings {

  private var shapes = Array[Shape]()

  private val ctx: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def draw(): Unit = {
    shapes.map(x => x match {
      case square: Square => draw(square)
      case shape: Shape => draw(shape)
    })
  }

  def draw(s: Shape): Unit = {

  }

  def draw(square: Square): Unit = {
    val style = square.color
    ctx.fillStyle = style
    ctx.fillRect(square.x, square.y, square.side, square.side)
    ctx.stroke()
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

  lazy val windowHeight = g.window.innerHeight
  lazy val windowWidth= g.window.innerWidth

  //tres temporaire, juste pour faire des tests
  val gridHeight = 800
  val gridWidth = 800
  // var game = new Game()
  val boardSquareList = Array.ofDim[Square](NumberOfSquaresWidth , NumberOfSquaresHeight)

  //private var shapes = Array[Shape]()

  def initRender() = {

    val squareSide = 40
    canvas.width = gridWidth
    canvas.height = gridHeight
    val head = new Square(0, 0, 0)
    //renderHead(head)
    println("initRender")

    for (i <- 0 to boardSquareList.length -1 ) {
      for (j <- 0 to boardSquareList(i).length -1) {
        boardSquareList(i)(j) = new Square(i * squareSide, j * squareSide, squareSide)
      }
    }
    draw()

  }

  def resetGame(): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.clearRect(0, 0, 800, 800)

  }
}

