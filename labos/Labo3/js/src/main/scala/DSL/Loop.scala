package DSL

import scala.scalajs.js
import js.Dynamic.{ global => g }

//aucune caliss didee comme thread avec scala c rough, c du copy paste ce code la
class Loop() {

  var then = js.Date.now()
  def loop(update: Double => Unit, render: () => Unit): () => Unit = () => {
    g.window.requestAnimationFrame(loop(update, render))
    val now = js.Date.now()
    val delta: Double = now - then
      update(delta / 1000)
      render()
      then = now
  }

  def start(update: Double => Unit, render: () => Unit) = {
    loop(update, render)()
  }

}