package DSL

import DSL._
import scala.util.Random

abstract class Game(canvasy: Canvasy, gameHeight: Int, gameWidth: Int) extends Settings {
  println("GAME")
  var gameOver = false
  var win = false
  //TODO renomer compteur
  var compteur = 0

  val board = Array.ofDim[Tile](gameWidth, gameHeight)

  def initGame(): Unit = {

    for (i <- 0 to board.length - 1) {
      for (j <- 0 to board(i).length - 1) {
        val square: Square = new Square(pixelSize * i, pixelSize * j, pixelSize)
        square change Color("black")
        board(i)(j) = new Tile(square)
        canvasy += Array(square)
      }
    }
  }

  def resetGameBoard(): Unit = {
    for (i <- 0 to board.length - 1) {
      for (j <- 0 to board(i).length - 1) {
        board(i)(j).tileType(TileType.Empty)
      }
    }
    canvasy.resetGame()
  }

  def checkCollisions(x: Int, y: Int, top: Int, bottom: Int, left: Int, right: Int): Boolean = {

    //verif collision avec les cotes
    (x <= bottom || x >= top || y >= right || y <= left)

  }

  def isGameOver(): Unit = {
    if (gameOver) {
      newGame(()=>{})
    }
  }

  def newGame(function:()=>Unit): Unit = {
    resetGameBoard()
    function()
    gameOver = false
  }

  def gameWon() = {
    gameOver = true
  }

  def update(function: () => Unit) = {

    if (!gameOver) {
      compteur += 1
      if (compteur % GameSpeed == 0) {
        //put your update here
        function()
        isGameOver()
      }
    }
  }
}
