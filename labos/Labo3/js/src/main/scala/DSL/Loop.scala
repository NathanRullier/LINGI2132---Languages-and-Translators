package DSL

import scala.scalajs.js
import org.scalajs.dom.window

//aucune caliss didee comme thread avec scala c rough, c du copy paste ce code la
class Loop() {

  def loop (update: () => Unit, render: () => Unit): Unit ={
        window.requestAnimationFrame((deltaTime: Double) => {
          loop(update, render)
        })
      update()
      render()
  }
  def start(update: () => Unit, render: () => Unit) = {
    loop(update, render)
  }

}
