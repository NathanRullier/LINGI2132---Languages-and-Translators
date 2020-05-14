package DSL

import scala.scalajs.js
import org.scalajs.dom.window

/**
 * The loop class handles everything related to the Loop.
 * @param timeBetweenLoopMS this is the time between each call to the render and the render.
 */
class Loop(var timeBetweenLoopMS: Int) {
  timeBetweenLoopMS = timeBetweenLoopMS / 60
  var timer: Int = 0

  /**
   *  Loop function called to update and render avec X milliseconds
   * @param update The update function
   * @param render The render function
   */
  def loop (update: () => Unit, render: () => Unit): Unit = {
    //println("timer "+ timer)
    window.requestAnimationFrame((deltaTime: Double) => {
        loop(update, render)
      })
     if (timer == timeBetweenLoopMS) {
       update()
       render()
     } else timer += 1
  }

  /**
   * Starter of the loop
   * @param update The update function
   * @param render The render function
   */
  def start(update: () => Unit, render: () => Unit) = {
    loop(update, render)
  }
}
