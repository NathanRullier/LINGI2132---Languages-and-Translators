package DSLDemo

sealed trait Shape {
  type A <: Shape

  def and(s: Shape): ComposedShape = {
    if (s.isInstanceOf[ComposedShape]) {
      ComposedShape(List(this) ::: s.asInstanceOf[ComposedShape].l)
    }
    else if (s.isInstanceOf[Shape]) {
      ComposedShape(List(this) ::: List(s))
    }
    else {
      ComposedShape(List(this))
    }
  }

  def change(property: CanvasElementModifier[A]): Unit = ???

  def moveX(v: Int): Unit = ???

  def moveY(v: Int): Unit = ???
}

sealed trait ShapeAttributes {
  var color: String = "red"

  def color(s: String): Unit = {
    color = s
  }

  var strokeWidth: Int = 1

  def strokeWidth(i: Int): Unit = {
    strokeWidth = i
  }

  // Add more attributes here
}

case class ComposedShape(var l: List[Shape]) extends Shape {

  def map(f: Shape => Shape): ComposedShape = {
    ComposedShape(l.map(f))
  }

  def flatMap(f: Shape => Iterable[Shape]): ComposedShape = {
    ComposedShape(l.flatMap(f))
  }

  def foreach[B](f: Shape => B): Unit = {
    l.map(f)
  }

  def apply(i: Int): Shape = {
    l(i)
  }

  override def moveX(v: Int): Unit = {
    l.map(x => x.moveX(v))
  }

  override def moveY(v: Int): Unit = {
    l.map(x => x.moveY(v))
  }

  override def change(property: CanvasElementModifier[A]): Unit = {

   l.map(x => x.change(property.asInstanceOf[CanvasElementModifier[x.A]]))
  }

  override def and(s: Shape): ComposedShape = {
    if (s.isInstanceOf[ComposedShape]) {
      ComposedShape(l ::: s.asInstanceOf[ComposedShape].l)
    }
    else if (s.isInstanceOf[Shape]) {
      ComposedShape(l ::: List(s))
    }
    else {
      ComposedShape(l)
    }

  }

  def ++(composedShape: ComposedShape): ComposedShape = {
    ComposedShape(l ++ composedShape.l)
  }


}

case class Rectangle(var x: Int, var y: Int, var width: Int, var height: Int) extends Shape with ShapeAttributes {
  type A = Rectangle

  override def moveX(v: Int): Unit = {
    x += v
  }

  override def moveY(v: Int): Unit = {
    y += v
  }

  override def change(property: CanvasElementModifier[Rectangle]): Unit = {
    property.change(this)
  }
}

case class Circle(var x: Int, var y: Int, var radius: Int) extends Shape with ShapeAttributes {
  type A = Circle

  override def moveX(v: Int): Unit = {
    x += v
  }

  override def moveY(v: Int): Unit = {
    y += v
  }

  override def change(property: CanvasElementModifier[Circle]): Unit = {
    property.change(this)
  }
}

object ComposedShapeImplicits {
  implicit def arrayCircleToComposedShape(a: Array[Circle]) = ComposedShape(a.toList)

  implicit def arrayRectangleToComposedShape(a: Array[Rectangle]) = ComposedShape(a.toList)
}