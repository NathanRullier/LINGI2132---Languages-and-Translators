package DSL

import DSL._
import scala.util.Random

trait Settings {

  // 60 / Game speed = X images per seconds
  var GameSpeed = 8
  var NumberOfSquaresWidth = 20
  var NumberOfSquaresHeight = 20
  var BasicSnakeLength = 3
  var SnakeLengthToWin = (NumberOfSquaresWidth * NumberOfSquaresHeight) / 2

}
