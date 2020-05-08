package DSL

class Game  {

  //var snake = Snake(head = Block(Position(0, 0), Colors.nextColor.rgb))

  var numberOfSquaresWidth = 40
  var numberOfSquaresHeight = 40
  var directionDoneInLastSecond = false
  var blocksEaten = 0
  var direction = Direction.Right
  var isGameOver = false
  var win = false
  // val boardSquareList
  val boardSquareList =
    for {
      //pas sur si cest width/height
      x <- 0 until numberOfSquaresWidth
      y <- 0 until numberOfSquaresHeight
    } Square(x,y,10, TileType.Empty,0)

  UserInputs.initInputsListener()

  def updatedirection(): Unit = {
    //val predirection = direction
    direction =
      if (UserInputs.holdLeft && (direction != Direction.Right)) Direction.Left
      else if (UserInputs.holdRight && (direction != Direction.Left)) Direction.Right
      else if (UserInputs.holdUp && (direction != Direction.Down)) Direction.Up
      else if (UserInputs.holdDown && (direction != Direction.Up)) Direction.Down
      else direction
  }
  
  def update(seconds: Int) = {

  }
}