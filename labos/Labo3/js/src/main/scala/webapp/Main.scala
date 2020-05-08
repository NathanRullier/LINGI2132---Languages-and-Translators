package webapp

import DSL._
import org.scalajs.dom.{html, document}
import org.scalajs.dom

object Main {

  def main(args: Array[String]): Unit = {
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    initGame(canvas)
  }

  def initGame(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    document.body.appendChild(canvas)
    val canvasy = new Canvasy(canvas)
    //canvasy.render()
    //canvasy.initRender
    //aaboardSquareList[0].print()
  }

//  def scalaJSDemo(c: html.Canvas): Unit = {
//    val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
//    val w = 300
//    c.width = w
//    c.height = w
//
//    ctx.strokeStyle = "blue"
//    ctx.lineWidth = 3
//    ctx.beginPath()
//    ctx.moveTo(w/3, 0)
//    ctx.lineTo(w/3, w/3)
//    ctx.moveTo(w*2/3, 0)
//    ctx.lineTo(w*2/3, w/3)
//    ctx.moveTo(w, w/2)
//    ctx.arc(w/2, w/2, w/2, 0, 3.14)
//
//    ctx.stroke()
//
//    useMySuperDSL(c)
//  }
//
//
//  def useMySuperDSL(canvas: html.Canvas): Unit = {
//    implicit def arrayCircleToComposedShape(a: Array[Circle]) = ComposedShape(a.toList)
//    implicit def arrayRectangleToComposedShape(a: Array[Rectangle]) = ComposedShape(a.toList)
//    // After you've done the first part of the project, everything should
//    // compile and do the expected behaviour
//    val canvasy = new Canvasy(canvas)
//    val circles = Array.fill(4)(Circle(50, 0, 0))
//    val rectangles = Array.tabulate(5)(i => Rectangle(i*10, i*10, 10, 30))
//    canvasy += circles
//    canvasy += rectangles
//    // First we can modify property of Shapes by modifying their property directly
//    circles(0) color "red"
//    rectangles(0) strokeWidth 10
//    rectangles(1) moveX 10
//    // We should also be able to do the same on a group of shapes
//    // (list, array, iterables, ...)
//    circles moveX 20
//    // We can also change property using the CanvasElementModifier trait
//    circles change Color("blue")
//    // We can group the shapes easily with the keyword and
//    val superGroupOfShapes = circles and rectangles
//    // And of course, we have foreach/map/flatmap available
//    (rectangles(0) and circles(1)).foreach(_ moveY 30)
//    // We should also be able to use common operators to group shapes
//    val anotherSuperGroup = rectangles ++ circles
//    // We can get back the elements by their index
//    val s = anotherSuperGroup(0)
//
//    s change Radius(10)
//    // Take care that some property change should not compile, like this one
//    //4ectangles(0) + circles(0)) change Width(30)
//    // because Circles have no width
//    // You can have a nice draw function to draw all of this on the canvas
//    canvasy.draw()
//  }


}