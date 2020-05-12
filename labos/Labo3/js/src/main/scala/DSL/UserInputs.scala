package DSL

import DSL._
import scala.scalajs.js
import org.scalajs.dom
import scala.collection.mutable.HashMap

object UserInputs {

  private val keysDown = HashMap[Int, Boolean]()

  var onLeftKeyPressed: () => Unit = () => {}
  var onUpKeyPressed: () => Unit = () => {}
  var onRightKeyPressed: () => Unit = () => {}
  var onDownKeyPressed: () => Unit = () => {}

  def initInputsListener(): Unit = {
    dom.document.addEventListener("keydown", (e: dom.KeyboardEvent) => {
      e.keyCode match {
        case 37 => onLeftKeyPressed()
        case 38 => onUpKeyPressed()
        case 39 => onRightKeyPressed()
        case 40 => onDownKeyPressed()
      }
    }, false)
  }
}
