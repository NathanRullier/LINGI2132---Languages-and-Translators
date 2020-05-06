package DSL

import org.scalajs.dom.html

class Canvasy(canvas: html.Canvas) {

  private var shapes = Array[Shape]()
  def draw(): Unit = {}
  def += (circles : Array[Circle] ) { shapes = shapes ++ circles }
  def += (rectangles : Array[Rectangle] ) { shapes = shapes ++ rectangles }

  private def renderTiles(tiles: List[Tile]) = {

    for (block <- blocks) {
      ctx.fillStyle = block.style
      ctx.fillRect(block.pos.x, block.pos.y, Block.BlockSize, Block.BlockSize)
    }
  }

}

