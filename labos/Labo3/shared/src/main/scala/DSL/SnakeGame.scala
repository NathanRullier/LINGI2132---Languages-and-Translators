package DSL

import DSL._
import scala.util.Random

class SnakeGame(canvasy: Canvasy, gameHeight: Int, gameWidth: Int) extends Game(canvasy, gameHeight, gameWidth) {
  var direction = Direction.Right
  var lastDirection = Direction.Right
  var snake = new Snake(3, 3, BasicSnakeLength)

  override def initGame(): Unit = {
    super.initGame()
    generateNewFood()
  }

  def newGame(): Unit = {
    super.newGame(() => {
      direction = Direction.Right
      lastDirection = direction
      snake = new Snake(3, 3, BasicSnakeLength)
      generateNewFood()
    })
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
    direction = MovementHandler.updateDirection(lastDirection)
    super.update(() => {
      snake.update()
      moveSnake()
      gameWon()
    })
  }
}
