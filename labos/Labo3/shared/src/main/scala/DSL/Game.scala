package DSL

class Game(canvasy: Canvasy)  {

  var numberOfSquaresWidth = 20
  var numberOfSquaresHeight = 20
  var directionDoneInLastSecond = false
  var blocksEaten = 0
  var direction = Direction.Right
  var gameOver = false
  var win = false
  var compteur = 0
  var GAMESPEED = 20
  // val boardSquareList
  //  val tail = Seq( for{
  //    x <- 0 until numberOfSquaresWidth
  //    y <- 0 until numberOfSquaresHeight
  //  }
  //    Square(x,y,40, TileType.SnakeTail,0),
  //  )
 // trouver une implementation pour faire ca avec une boucle for?
  val tail = Seq(
    Square(1,0,40, TileType.SnakeTail,0),
    Square(2,0,40, TileType.SnakeTail,0)
  )

  var snake = new Snake(head = Square(3,0,40, TileType.SnakeHead,0),tail = tail);

  println(snake.head)

  def updateDirection(): Unit = {
    direction =
      if (UserInputs.holdLeft && (direction != Direction.Right)) Direction.Left
      else if (UserInputs.holdRight && (direction != Direction.Left)) Direction.Right
      else if (UserInputs.holdUp && (direction != Direction.Down)) Direction.Up
      else if (UserInputs.holdDown && (direction != Direction.Up)) Direction.Down
      else direction

    //println("direction: " +direction)
  }
  def isGameOver(): Unit ={
    if(gameOver) {
      canvasy.resetGame()
      snake = snake.copy(head = snake.head.copy(x=3,y=0))
      newGame()
    }
  }

  def newGame(): Unit ={
    direction = Direction.Right
    snake = snake.copy(head = snake.head.copy(x=3,y=0))
    gameOver = false
  }
  // Pas sur comment faire pour que le snake soit une var global en scala... entk a mettre clean plus tard
//  def newSnake(): = {
//    val tail = Seq(
//      Square(3,0,40, TileType.SnakeTail,0),
//      Square(3,0,40, TileType.SnakeTail,0)
//    )
//    val snake = new Snake(Square(3,0,40, TileType.SnakeHead,0),tail)
//  }

  def checkCollisions(): Unit ={

    //verif collisions avec les cotes
    if (snake.head.x <= -1 || snake.head.x >=numberOfSquaresWidth || snake.head.y >= numberOfSquaresHeight || snake.head.y <= -1){
      gameOver = true
      println("GAME OVER")
    }


  }

  def moveSnake(): Unit = {

    val modifWidth: Int =
      if (direction == Direction.Right) 1 else if (direction == Direction.Left) -1 else 0

    val modifHeight: Int =
      if (direction == Direction.Up) -1 else if (direction == Direction.Down) 1 else 0

    val newYHeadPosition = snake.head.y + modifHeight
    val newXHeadPosition = snake.head.x + modifWidth

    //println("snake x: "+snake.head.x+ " numberOfSquaresWidth " +numberOfSquaresWidth )
    //println("snake y: "+snake.head.y+ " numberOfSquaresHeight " +numberOfSquaresHeight )
    //println("new y " +newYHeadPosition)
    //println("new x " +newXHeadPosition)

    snake = snake.copy(head = snake.head.copy(x=newXHeadPosition,y=newYHeadPosition))

  }



  def update(seconds: Double) = {

    compteur += 1
    updateDirection()
    isGameOver()
    if(compteur%GAMESPEED == 0) moveSnake()
    checkCollisions()

    canvasy.renderHead(snake.head)

  }
}