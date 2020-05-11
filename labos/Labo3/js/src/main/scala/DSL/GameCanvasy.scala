package DSL

import DSL._
import javax.swing.Timer
import org.scalajs.dom.html
import scala.scalajs.js
import js.Dynamic.{global => g}
import org.scalajs.dom

class GridCanvasy(canvas: html.Canvas, gridHeight: Int, gridWidth: Int, pixelSize: Int) extends Canvasy(canvas, gridHeight * pixelSize, gridWidth * pixelSize) {
  val boardSquareList =
    Array.ofDim[Square](NumberOfSquaresWidthSnake, NumberOfSquaresHeightSnake)

  override def initRender(): Unit = {
    for (i <- 0 to boardSquareList.length - 1) {
      for (j <- 0 to boardSquareList(i).length - 1) {
        boardSquareList(i)(j) =
          new Square(i * pixelSize, j * pixelSize, pixelSize)
      }
    }
    super.initRender()
  }
}
