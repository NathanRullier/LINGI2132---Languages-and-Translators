package DSL

import DSL._

class Tile(square: Square) {

  private var _tileType = TileType.Empty
  private var _timer = 0

  def tileType(tileType: TileType.Value): Unit = {
    _tileType = tileType

    tileType match {
      case TileType.Empty => square change Color("black")
        _timer = 0
      case TileType.Snake => square change Color("blue")
      case TileType.Food  => square change Color("red")
    }
  }

  def timer(timer: Int): Unit = {
    _timer = timer
  }

  def tileType(): TileType.Value = _tileType

  def decrementTimer(): Unit = {
    if (_tileType == TileType.Snake) {
      _timer -= 1
      if (_timer == 0) {
        tileType(TileType.Empty)
      }
    }
  }

  def incrementTimer(): Unit = {

    if (_tileType == TileType.Snake && _timer != 0) {
      _timer += 1
    }
  }
}
