package DSL

import DSL._


object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object TileType extends Enumeration {
  val Empty, Snake, Food = Value
  val Timer = 0
}

class Tile{
  var Type = TileType.Empty
}

class Game(canvasy: Canvasy) extends Settings {

  var directionDoneInLastSecond = false
  var direction = Direction.Right
  var gameOver = false
  var win = false
  var compteur = 0
  val board = Array.ofDim[Tile](NumberOfSquaresWidth,NumberOfSquaresWidth)

  var snake = new Snake(3,3,3);


  //TODO class controller
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
      snake = new Snake(3,3,3)
      newGame()
    }
  }

  def newGame(): Unit ={
    direction = Direction.Right
    snake = new Snake(3,3,3)
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
    if (snake.headX <= -1 || snake.headX >=NumberOfSquaresWidth || snake.headY >= NumberOfSquaresHeight || snake.headY <= -1){
      gameOver = true
      println("GAME OVER")
    }


  }

  //TODO class controller
  def moveSnake(): Unit = {

    val modifWidth: Int =
      if (direction == Direction.Right) 1 else if (direction == Direction.Left) -1 else 0

    val modifHeight: Int =
      if (direction == Direction.Up) -1 else if (direction == Direction.Down) 1 else 0

    val newYHeadPosition = snake.headY + modifHeight
    val newXHeadPosition = snake.headX + modifWidth

    //println("snake x: "+snake.head.x+ " numberOfSquaresWidth " +numberOfSquaresWidth )
    //println("snake y: "+snake.head.y+ " numberOfSquaresHeight " +numberOfSquaresHeight )
    //println("new y " +newYHeadPosition)
    //println("new x " +newXHeadPosition)

    //snake = snake.copy(head = snake.head.copy(x=newXHeadPosition,y=newYHeadPosition))

  }



  def update(seconds: Double) = {

    compteur += 1
    updateDirection()
    isGameOver()
    if(compteur % GameSpeed == 0) moveSnake()
    checkCollisions()

    //canvasy.renderHead(snake.head)

  }
}