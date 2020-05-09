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
  var gridHeight = 800
  var gridWidth = 800
 // var game = new Game()
  val boardSquareList =
    for {
      //pas sur si cest width/height
      x <- 0 until 80
      y <- 0 until 80
    } Square(x,y,10, TileType.Empty,0)

  //private var shapes = Array[Shape]()

  def initRender(canvas: html.Canvas) = {

     canvas.width = gridWidth
     canvas.height = gridHeight
     val head = new Square(0,0,40, TileType.Empty,0)
      renderHead(head)
     //renderTiles(boardSquareList.toSeq)
     println("initRender")

  }
  private def renderSnakeGame(squares: Seq[Square]) = {

    renderTiles(squares)

  }
  def fkall() = {

  }
  def renderHead(square: Square) = {

    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

      val style =
        if(square.tileType == TileType.Empty) "#d1d1e0" //gris
        else if((square.tileType == TileType.SnakeHead) || (square.tileType == TileType.SnakeTail)) "#3366ff" //bleu
        else if(square.tileType == TileType.Food) "#ff0000" //rouge
        else "red" //rouge

      //println(style)
      ctx.fillStyle = style
      ctx.fillRect(square.x * square.size, square.y * square.size, square.size, square.size)

      //ctx.stroke()

  }

   def resetGame(): Unit ={
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.clearRect(0,0,800,800)

  }

  private def renderTiles(squares: Seq[Square]) = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    for (square <- squares) {
     // println("max")
      //square.draw();

      val style =
        if(square.tileType == TileType.Empty) "blue"
        else "red"

      ctx.fillRect(square.x * square.size, square.y * square.size, square.size, square.size)
      ctx.fillStyle = style
    }
  }
  initRender(canvas)
}

