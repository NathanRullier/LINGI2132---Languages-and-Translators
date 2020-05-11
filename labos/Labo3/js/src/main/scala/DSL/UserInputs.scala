package DSL

import DSL._
import scala.scalajs.js
import org.scalajs.dom
import scala.collection.mutable.HashMap

object UserInputs {

  private val keysDown = HashMap[Int, Boolean]()

  def initInputsListener() = {
    dom.document.addEventListener("keydown", (e: dom.KeyboardEvent) => {
      keysDown += e.keyCode -> true
    }, false)
    dom.document.addEventListener("keyup", (e: dom.KeyboardEvent) => {
      keysDown -= e.keyCode
    }, false)
  }

  def holdLeftKey = keysDown.contains(37)
  def holdUpKey = keysDown.contains(38)
  def holdRightKey = keysDown.contains(39)
  def holdDownKey = keysDown.contains(40)

}
