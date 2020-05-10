package DSL

import DSL._
import scala.scalajs.js
import js.Dynamic.{global => g}
import org.scalajs.dom
import scala.collection.mutable.HashMap

object UserInputs {

  private val keysDown = HashMap[Int, Boolean]()

  def initInputsListener() = {
    g.addEventListener("keydown", (e: dom.KeyboardEvent) => {
      keysDown += e.keyCode -> true

    }, false)

    g.addEventListener("keyup", (e: dom.KeyboardEvent) => {
      keysDown -= e.keyCode
    }, false)
  }

  def holdLeft = keysDown.contains(37)
  def holdUp = keysDown.contains(38)
  def holdRight = keysDown.contains(39)
  def holdDown = keysDown.contains(40)

}
