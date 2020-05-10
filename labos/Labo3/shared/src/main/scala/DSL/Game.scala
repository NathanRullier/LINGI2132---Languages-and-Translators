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
  var directionDoneInLastSecond = false
  var direction = Direction.Right
  var gameOver = false
  var win = false
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
        board(i)(j).timer(0)
        board(i)(j).tileType(TileType.Empty)
      }
    }
    canvasy.resetGame()
  }

  //TODO class controller
  def updateDirection(): Unit = {
    direction =
      if (UserInputs.holdLeft && (direction != Direction.Right)) Direction.Left
      else if (UserInputs.holdRight && (direction != Direction.Left))
        Direction.Right
      else if (UserInputs.holdUp && (direction != Direction.Down)) Direction.Up
      else if (UserInputs.holdDown && (direction != Direction.Up))
        Direction.Down
      else direction

    //println("direction: " +direction)
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
    resetGameBoard()
    snake = new Snake(3, 3, BasicSnakeLength)
    generateNewFood()
    gameOver = false
  }

  def checkCollisions(): Unit = {

    //verif collision avec les cotes (probablement devoir update les valeurs)
    if (snake.headX <= -1 || snake.headX >= NumberOfSquaresWidth || snake.headY >= NumberOfSquaresHeight || snake.headY <= -1) {
      println("COLLISION AVEC UN MUR")
      newGame()
    }
    //verif collision avec une pomme
    else if (board(snake.headX)(snake.headY).tileType == TileType.Food) {
      snake.length += 1
      println("MENOUM UN BON LEGUME")
      generateNewFood()
      board.map(x => x.map(y => y.incrementTimer()))
      //incrementer les timer de chaque tile snake ?
    }
    //verif collision avec sa queue
    else if (board(snake.headX)(snake.headY).tileType == TileType.Snake) {
      println("COLLISION AVEC QUEUE DU SERPENT")
      newGame()
    }
  }

  // Cette fonction pourrait etre dans un autre fichier appele food, a revoir...
  def generateNewFood(): Unit = {

    val newFoodPositionX = Random.nextInt(NumberOfSquaresWidth)
    val newFoodPositionY = Random.nextInt(NumberOfSquaresHeight)
    if (board(newFoodPositionX)(newFoodPositionY).tileType != TileType.Empty) {
      generateNewFood()
    } else board(newFoodPositionX)(newFoodPositionY).tileType(TileType.Food)
  }
  //TODO class controller
  def moveSnake(): Unit = {

    val modifWidth: Int =
      if (direction == Direction.Right) 1
      else if (direction == Direction.Left) -1
      else 0

    val modifHeight: Int =
      if (direction == Direction.Up) -1
      else if (direction == Direction.Down) 1
      else 0

    var newYHeadPosition = snake.headY + modifHeight
    var newXHeadPosition = snake.headX + modifWidth

    //println("snake x: "+snake.head.x+ " numberOfSquaresWidth " +numberOfSquaresWidth )
    //println("snake y: "+snake.head.y+ " numberOfSquaresHeight " +numberOfSquaresHeight )
    //println("new y " +newYHeadPosition)
    //println("new x " +newXHeadPosition)

    //Les 2 lignes sous dessous ne fonctionnent pas car les attributs de snake sont immutable.. je crois quon est oblige de copy

    //snake.headX = newXHeadPosition
    //snake.headY = newYHeadPosition

    //Nouveau Serpent
    snake.headX = newXHeadPosition
    snake.headY = newYHeadPosition
    checkCollisions()
    //Verif si le nouveau serpent entre en collision avec quelquechose sur le board ou les cotes
    // isGameOver()
    //Si tout est beau, on update le board
    board(snake.headX)(snake.headY).tileType(TileType.Snake)
    board(snake.headX)(snake.headY).timer(snake.length)
  }

  def gameWon() = {
    if (snake.length >= SnakeLengthToWin) gameOver = true

  }

  def update(seconds: Double) = {

    if (!gameOver) {
      compteur += 1
      updateDirection()
      if (compteur % GameSpeed == 0) {
        //updateDirection()
        board.map(x => x.map(y => y.decrementTimer()))
        moveSnake()
        gameWon()
      }

    }
  }
}
