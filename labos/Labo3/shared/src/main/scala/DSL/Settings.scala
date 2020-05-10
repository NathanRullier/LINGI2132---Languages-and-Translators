package DSL

import DSL._
import scala.util.Random

trait Settings {

  // 60 / Game speed = X images per seconds
  var GameSpeed = 8
  var NumberOfSquaresWidthSnake = 20
  var NumberOfSquaresHeightSnake = 20
  var BasicSnakeLength = 3
  var pixelSize = 40
  var SnakeLengthToWin = (NumberOfSquaresWidthSnake * NumberOfSquaresHeightSnake) / 2

}
