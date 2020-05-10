package DSL

import DSL._

class Snake(var posX: Int, var posY: Int, var length: Int) extends GameObject(posX,posY) {
  private var snakeBody: List[Tile] = List()

  def addBody(tile: Tile): Unit = {
    tile.tileType(TileType.Snake)
    tile.timer(length)
    snakeBody = snakeBody :+ tile
  }

  def update(): Unit = {
    snakeBody.map(x => x.decrementTimer())
    snakeBody = snakeBody.filter(_.timer() > 0)
  }

  def eatFood(): Unit = {
    snakeBody.map(x => x.incrementTimer())
    length += 1
  }
}