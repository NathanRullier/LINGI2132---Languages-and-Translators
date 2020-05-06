package DSL

import DSL._
import scala.scalajs.js
import js.Dynamic.{ global => g }
import org.scalajs.dom
import scala.collection.mutable.HashMap

object Direction extends Enumeration {
  val Left, Right, Up, Down = Value
}

object UserInputs {

  private val keysDown = HashMap[js.Number, Boolean]()

  def init() = {
    g.addEventListener("keydown", (e: dom.KeyboardEvent) => {
      keysDown += e.keyCode -> true
    }, false)

    g.addEventListener("keyup", (e: dom.KeyboardEvent) => {
      keysDown -= e.keyCode
    }, false)
  }

  def Left = keysDown.contains(37)
  def Up = keysDown.contains(38)
  def Right = keysDown.contains(39)
  def Down = keysDown.contains(40)

}