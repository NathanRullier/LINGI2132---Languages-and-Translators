package DSL

import DSL._

case class Snake(var headX: Int, var headY: Int, var length: Int) {
  private var snakeBody: List[Tile] = List()

  def addBody(tile: Tile): Unit = {
    tile.tileType(TileType.Snake)
    tile.timer(length)
    snakeBody = snakeBody :+ tile
  }

  def update(): Unit = {
    println(snakeBody.length)
    snakeBody.map(x => x.decrementTimer())
    snakeBody = snakeBody.filter(_.timer()>0)
  }

  def eatFood(): Unit = {
    snakeBody.map(x => x.incrementTimer())
    length += 1
  }
}