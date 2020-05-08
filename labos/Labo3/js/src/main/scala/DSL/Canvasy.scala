package DSL

import javax.swing.Timer
import org.scalajs.dom.html
import org.scalajs.dom

class Canvasy(canvas: html.Canvas) {

  private var shapes = Array[Shape]()
  def draw(): Unit = {}
  def += (circles : Array[Circle] ) { shapes = shapes ++ circles }
  def += (rectangles : Array[Rectangle] ) { shapes = shapes ++ rectangles }
  def += (squares : Array[Square] ) { shapes = shapes ++ squares }
  var gridHeight = 400
  var gridWidth = 400
  var game = new Game()
  //private var shapes = Array[Shape]()


  def initRender(canvas: html.Canvas) = {
     canvas.width = gridWidth
     canvas.height = gridHeight
    // renderTiles(squares)
    //GameLoop
     println("initRender")

  }
  private def renderSnakeGame(squares: List[Square]) = {

    renderTiles(squares)

  }
  private def renderTiles(squares: List[Square]) = {

    for (square <- squares) {

      //square.draw();

      val style =
        if(square.tileType == TileType.Empty) "blue"
        else "red"

     // ctx.fillRect(square.x * square.size, square.y * square.size, square.size, square.size)
      // ctx.fillStyle = style
    }
  }
  initRender(canvas)
}

