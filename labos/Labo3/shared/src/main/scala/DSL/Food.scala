package DSL

import DSL._
import scala.util.Random

object Food extends Settings {

  def generateNewFood(): Unit ={

    val foodPositionX = Random.nextInt(NumberOfSquaresWidth)
    val foodPositionY = Random.nextInt(NumberOfSquaresHeight)

  }

}