package DSL

import scala.scalajs.js
import org.scalajs.dom.window

class Loop(var timeBetweenLoopMS: Int) {
  timeBetweenLoopMS = timeBetweenLoopMS / 60
  var timer: Int = 0

  def loop (update: () => Unit, render: () => Unit): Unit = {
    window.requestAnimationFrame((deltaTime: Double) => {
        loop(update, render)
      })
     if (timer == timeBetweenLoopMS) {
       update()
       render()
     } else timer += 1
  }
  def start(update: () => Unit, render: () => Unit) = {
    loop(update, render)
  }
}
