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
    val canvasy = new Canvasy(canvas, snakeGridHeight * pixelSize, snakeGridWidth * pixelSize)
    canvasy.initRender()

    val background = new Square(0, 0, pixelSize * snakeGridWidth)
    background change Color("black")
    canvasy += Array(background)

    val newSnake = () => List.fill(3)(new Square(0, 0, pixelSize))
    //init new Snake
    val snake = ComposedShape(newSnake())
    //init a new Food
    val food = new Square(Random.nextInt(snakeGridWidth) * pixelSize, Random.nextInt(snakeGridWidth) * pixelSize, pixelSize)
    food change Color("blue")

    canvasy += Array(snake)
    canvasy += Array(food)

    var direction = Direction.Right
    UserInputs.onLeftKeyPressed = () => { if (direction != Direction.Right) direction = Direction.Left }
    UserInputs.onRightKeyPressed = () => { if (direction != Direction.Left) direction = Direction.Right }
    UserInputs.onUpKeyPressed = () => { if (direction != Direction.Down) direction = Direction.Up }
    UserInputs.onDownKeyPressed = () => { if (direction != Direction.Up) direction = Direction.Down }

    UserInputs.initInputsListener()

    val movement = () => {
      val head = new Square(snake(0).x, snake(0).y, snake(0).side)
      direction match {
        case Direction.Left => head moveX -pixelSize
        case Direction.Right => head moveX pixelSize
        case Direction.Up => head moveY -pixelSize
        case Direction.Down => head moveY pixelSize
      }
      snake.addFirst(head)
      snake.removeLast()
    }

    val collisions = () => {
      if (CollisionHandler.collisionObjxBorders(snake(0), background)) {
        snake.l = newSnake()
        direction = Direction.Right
      }
      snake.l.slice(1, snake.size()).foreach(x => {
        x match {
          case square: Square => if (CollisionHandler.perfectCollisionObjxObj(snake(0), square)) {
            snake.l = newSnake()
            direction = Direction.Right
          }
        }
      })
      if (CollisionHandler.perfectCollisionObjxObj(snake(0).x, snake(0).y, food.x, food.y)) {
        food.x = Random.nextInt(snakeGridWidth) * pixelSize
        food.y = Random.nextInt(snakeGridHeight) * pixelSize
        snake.addLast(new Square(snake(0).x, snake(0).y, pixelSize))
      }
    }

    val loop = new Loop(0)
    var compteur = 0

    loop.start(() => {
      compteur += 1
      if (compteur % GameSpeed == 0) {
        movement()
        collisions()
      }
    }, () => canvasy.draw())

  }

}