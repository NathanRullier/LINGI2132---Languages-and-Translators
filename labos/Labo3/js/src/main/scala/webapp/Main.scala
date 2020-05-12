package webapp

import DSL._
import org.scalajs.dom.{html, document}
import org.scalajs.dom
import scala.util.Random

object Main {

  def main(args: Array[String]): Unit = {
    initGame()

  }

  def initGame(): Unit = {
    val snakeGridHeight: Int = 20
    val snakeGridWidth: Int = 20
    val pixelSize = 40
    var GameSpeed = 8

    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    document.body.appendChild(canvas)
    val canvasy = new GridCanvasy(canvas, snakeGridHeight, snakeGridWidth, pixelSize)
    canvasy.initRender()
    //val snakeGame = new SnakeGame(snakeGridHeight, snakeGridWidth)
    //snakeGame.initGame()
    val background = new Square(0, 0, pixelSize * snakeGridWidth)
    background change Color("black")
    canvasy += Array(background)

    val snake = ComposedShape(List.fill(3)(new Square(0, 0, pixelSize)))
    //init a new Food
    val newFoodPositionX = Random.nextInt(snakeGridWidth) * pixelSize
    val newFoodPositionY = Random.nextInt(snakeGridHeight) * pixelSize
    println("x: " +newFoodPositionX)
    println("y: " +newFoodPositionY)
    val food = new Square(newFoodPositionX,newFoodPositionY, pixelSize)
    food change Color("red")
    //init a new Snake
    snake change Color("blue")

    canvasy += Array(snake)
    canvasy += Array(food)

    var direction = Direction.Right
    UserInputs.onLeftKeyPressed = () => {
      direction = Direction.Left
    }
    UserInputs.onRightKeyPressed = () => {
      direction = Direction.Right
    }
    UserInputs.onUpKeyPressed = () => {
      direction = Direction.Up
    }
    UserInputs.onDownKeyPressed = () => {
      direction = Direction.Down
    }

    UserInputs.initInputsListener()

    val movement = () => {
      val head = new Square(snake(0).x, snake(0).y, snake(0).side)
      head change Color("blue")
      direction match {
        case Direction.Left => head moveX -pixelSize
        case Direction.Right => head moveX pixelSize
        case Direction.Up => head moveY -pixelSize
        case Direction.Down => head moveY pixelSize
      }
      snake.addFirst(head)
      snake.removeLast()
    }

    //snakeGame.board.map(x => canvasy += x.map(y => y.getSquare()))
    //canvasy.initRender()
    val loop = new Loop(0)
    //loop.start(snakeGame.update, () => canvasy.draw())
    var compteur = 0
    loop.start(() => {
      compteur += 1
      if (compteur % GameSpeed == 0) {
        movement()

        if (CollisionHandler.perfectCollisionObjxObj(snake(0).x, snake(0).y, food.x, food.y)) {
          println("menoum")
          food.x = Random.nextInt(snakeGridWidth) * pixelSize
          food.y = Random.nextInt(snakeGridHeight) * pixelSize
        }
        if (CollisionHandler.collisionObjxBorders(snake(0).x,snake(0).y,0,0,snakeGridWidth*pixelSize,snakeGridWidth*pixelSize)) {
          println("pouf")
          snake(0).x = 3
          snake(0).y = 3
           direction = Direction.Right
        }
      }
    }, () => canvasy.draw())

    //println(canvasy.getShapes()(0).x)
//    for (i <- 0 to canvasy.getShapes().length - 1) {
//      for (j <- 0 to canvasy.getShapes().length - 1) {
//        if((canvasy.getShapes()(i).x == canvasy.getShapes()(j).x) && (canvasy.getShapes()(i).y == canvasy.getShapes()(j).y) && (canvasy.getShapes()(i).color == "red") && (canvasy.getShapes()(j).color == "blue")) {
//
//        }
//      }
//    }

    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

// def generateNewFood(): Unit = {
//   food.x = Random.nextInt(snakeGridWidth) * pixelSize
//   food.y = Random.nextInt(snakeGridHeight) * pixelSize
//   if () {
//      generateNewFood()
//    } else board(newFoodPositionX)(newFoodPositionY).tileType(TileType.Food)
//  }

}