package DSL

import DSL._
import scala.util.Random

object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object TileType extends Enumeration {
  val Empty, Snake, Food = Value
}

class Game(canvasy: Canvasy) extends Settings {
  println("GAME")
  var direction = Direction.Right
  var gameOver = false
  var win = false
  var lastDirection = Direction.Right
  var compteur = 0
  var snake = new Snake(3, 3, BasicSnakeLength)

  val board = Array.ofDim[Tile](NumberOfSquaresWidth, NumberOfSquaresWidth)

  def initGame(): Unit = {

    for (i <- 0 to board.length - 1) {
      for (j <- 0 to board(i).length - 1) {
        val square: Square = new Square(40 * i, 40 * j, 40)
        square change Color("black")
        board(i)(j) = new Tile(square)
        canvasy += Array(square)
      }
    }
    generateNewFood()
  }

  def resetGameBoard(): Unit = {
    for (i <- 0 to board.length - 1) {
      for (j <- 0 to board(i).length - 1) {
        board(i)(j).tileType(TileType.Empty)
      }
    }
    canvasy.resetGame()
  }

  def isGameOver(): Unit = {
    println("Maxime")
    if (gameOver) {
      resetGameBoard()
      snake = new Snake(3, 3, BasicSnakeLength)
      newGame()
    }
  }

  def newGame(): Unit = {
    direction = Direction.Right
    lastDirection = direction
    resetGameBoard()
    snake = new Snake(3, 3, BasicSnakeLength)
    generateNewFood()
    gameOver = false
  }

  def checkCollisions(): Unit = {

    //verif collision avec les cotes
    if (snake.posX <= -1 || snake.posX >= NumberOfSquaresWidth || snake.posY >= NumberOfSquaresHeight || snake.posY <= -1) {
      println("COLLISION AVEC UN MUR")
      newGame()
    }
    //verif collision avec une pomme
    else if (board(snake.posX)(snake.posY).tileType == TileType.Food) {
      snake.eatFood()
      println("MENOUM UN BON LEGUME")
      generateNewFood()
    }
    //verif collision avec sa queue
    else if (board(snake.posX)(snake.posY).tileType == TileType.Snake) {
      println("COLLISION AVEC QUEUE DU SERPENT")
      newGame()
    }
  }

  def generateNewFood(): Unit = {

    val newFoodPositionX = Random.nextInt(NumberOfSquaresWidth)
    val newFoodPositionY = Random.nextInt(NumberOfSquaresHeight)
    if (board(newFoodPositionX)(newFoodPositionY).tileType != TileType.Empty) {
      generateNewFood()
    } else board(newFoodPositionX)(newFoodPositionY).tileType(TileType.Food)
  }

  //TODO class controller
  def moveSnake(): Unit = {
    lastDirection = direction
    val modifWidth = MovementHandler.getWidthVariation(direction)
    val modifHeight = MovementHandler.getHeightVariation(direction)
    snake.posX += modifWidth
    snake.posY += modifHeight
    checkCollisions()
    snake.addBody(board(snake.posX)(snake.posY))
    //Verif si le nouveau serpent entre en collision avec quelquechose sur le board ou les cotes
    // isGameOver()
    //Si tout est beau, on update le board
  }

  def gameWon() = {
    if (snake.length >= SnakeLengthToWin) gameOver = true
  }

  def update(seconds: Double) = {

    if (!gameOver) {
      compteur += 1
      direction = MovementHandler.updateSnakeDirection(lastDirection)
      if (compteur % GameSpeed == 0) {
        //updateDirection()
        snake.update()
        moveSnake()
        gameWon()
      }
    }
  }
}
