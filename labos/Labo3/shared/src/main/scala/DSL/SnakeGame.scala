package DSL

import DSL._
import scala.util.Random

object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object TileType extends Enumeration {
  val Empty, Snake, Food = Value
}

class SnakeGame(canvasy: Canvasy, gameHeight: Int, gameWidth: Int) extends Game(canvasy, gameHeight, gameWidth) {
  println("GAME")
  var direction = Direction.Right
  var lastDirection = Direction.Right
  var snake = new Snake(3, 3, BasicSnakeLength)

  override def initGame(): Unit = {

    super.initGame()
    generateNewFood()
  }

  //TODO class controller
  def updateDirection(): Unit = {
    direction =
      if (UserInputs.holdLeft && (lastDirection != Direction.Right)) Direction.Left
      else if (UserInputs.holdRight && (lastDirection != Direction.Left))
        Direction.Right
      else if (UserInputs.holdUp && (lastDirection != Direction.Down)) Direction.Up
      else if (UserInputs.holdDown && (lastDirection != Direction.Up))
        Direction.Down
      else direction

    //println("direction: " +direction)
  }

  override def isGameOver(): Unit = {

    if (gameOver) {
      snake = new Snake(3, 3, BasicSnakeLength)
      super.isGameOver()
    }
  }

  override def newGame(): Unit = {
    direction = Direction.Right
    lastDirection = direction
    snake = new Snake(3, 3, BasicSnakeLength)
    generateNewFood()
    super.newGame()
  }

  def checkCollisions(): Unit = {

    //verif collision avec les cotes
    if (snake.posX <= -1 || snake.posX >= gameHeight || snake.posY >= gameHeight || snake.posY <= -1) {
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

    val newFoodPositionX = Random.nextInt(gameWidth)
    val newFoodPositionY = Random.nextInt(gameHeight)
    if (board(newFoodPositionX)(newFoodPositionY).tileType != TileType.Empty) {
      generateNewFood()
    } else board(newFoodPositionX)(newFoodPositionY).tileType(TileType.Food)
  }

  //TODO class controller
  def moveSnake(): Unit = {
    lastDirection = direction
    val modifWidth: Int =
      if (direction == Direction.Right) 1
      else if (direction == Direction.Left) -1
      else 0

    val modifHeight: Int =
      if (direction == Direction.Up) -1
      else if (direction == Direction.Down) 1
      else 0

    //Nouveau Serpent
    snake.posX += modifWidth
    snake.posY += modifHeight
    checkCollisions()
    snake.addBody(board(snake.posX)(snake.posY))
    //Verif si le nouveau serpent entre en collision avec quelquechose sur le board ou les cotes
    // isGameOver()
    //Si tout est beau, on update le board
  }

  override def gameWon(): Unit = {
    if (snake.length >= SnakeLengthToWin) this.gameWon()
  }

  def update(): Unit = {
    updateDirection()
    super.update(() => {
      snake.update()
      moveSnake()
      gameWon()
    })
  }
}
