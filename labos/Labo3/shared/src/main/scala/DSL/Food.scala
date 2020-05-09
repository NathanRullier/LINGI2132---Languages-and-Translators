package DSL

import DSL._
import scala.util.Random

object Food {

  def newFood(): Unit ={

    val randomX = Random.nextInt(20)
    val randomY = Random.nextInt(20)
  }

}