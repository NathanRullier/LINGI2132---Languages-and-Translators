package DSL
import DSL._

/**
 * modify target element (canvas)
 * @tparam ApplyOn, Shape to modify
 */
trait CanvasElementModifier[-ApplyOn <: Shape] {
  def change(x: ApplyOn): Unit
}
/**
 * Change de color of the shape
 * @param c new color to
 */
case class Color(c: String) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[ShapeAttributes].color = c
  }
}
/**
 * Change the width of the stroke
 * @param c variable to change
 */
case class StrokeWidth(s: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[ShapeAttributes].strokeWidth = s
  }
}

/**
 * change width of rectangle
 * @param w
 */
case class Width(w: Int) extends CanvasElementModifier[Rectangle] {
  override def change(x: Rectangle): Unit = {
    x.width = w
  }
}
/**
 * change Height of rectangle
 * @param w new height
 */
case class Height(h: Int) extends CanvasElementModifier[Rectangle] {
  override def change(x: Rectangle): Unit = {
    x.height = h
  }
}
/**
 * change radius circle
 * @param w change radius
 */
case class Radius(r: Int) extends CanvasElementModifier[Circle] {
  override def change(x: Circle): Unit = {
    x.radius = r
  }
}