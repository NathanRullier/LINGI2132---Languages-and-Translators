package DSLDemo

import DSLDemo._
import scala.util.{Try, Failure, Success}

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
    if (x.isInstanceOf[Rectangle]) {
      x.asInstanceOf[Rectangle].width = w
    } else {
      throw new Exception("is not rectangle")
    }
  }
}

case class Height(h: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x.isInstanceOf[Rectangle]) {
      x.asInstanceOf[Rectangle].height = h
    } else {
      throw new Exception("is not a rectangle")
    }
  }
}

case class Radius(r: Int) extends CanvasElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x.isInstanceOf[Circle]) {
      x.asInstanceOf[Circle].radius = r
    } else {
      throw new Exception("is not a circle")
    }
  }
}