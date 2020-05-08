package DSLDemo

import DSLDemo._

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

case class Width(w: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[Rectangle].width = w
  }
}

case class Height(h: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[Rectangle].height = h
  }
}

case class Radius(r: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    x.asInstanceOf[Circle].radius = r
  }
}