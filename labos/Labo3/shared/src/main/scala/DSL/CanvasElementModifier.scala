package DSL

import DSL._

trait CanvasElementModifier[-ApplyOn <: Shape] {
  def change(x: ApplyOn): Unit
}

case class Color(c: String) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[ShapeAttributes].color = c
  }
}

case class StrokeWidth(s: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[ShapeAttributes].strokeWidth = s
  }
}

case class Width(w: Int) extends CanvasElementModifier[Rectangle] {
  override def change(x: Rectangle): Unit = {
    x.width = w
  }
}

case class Height(h: Int) extends CanvasElementModifier[Rectangle] {
  override def change(x: Rectangle): Unit = {
    x.height = h
  }
}

case class Radius(r: Int) extends CanvasElementModifier[Circle] {
  override def change(x: Circle): Unit = {
    x.radius = r
  }
}

case class Side(s: Int) extends CanvasElementModifier[Square] {
  override def change(x: Square): Unit = {
    x.side = s
  }
}