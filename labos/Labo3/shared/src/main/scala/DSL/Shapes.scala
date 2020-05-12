package DSL

sealed trait Shape {
  type A <: Shape

  def and(s: Shape): ComposedShape[Shape] = {
    if (s.isInstanceOf[ComposedShape[A]]) {
      println(1)
      ComposedShape(List(this) ::: s.asInstanceOf[ComposedShape[A]].l)
    }
    else if (s.isInstanceOf[A]) {
      println(2)
      ComposedShape[Shape](List(this) ::: List(s))
    }
    else if(s.isInstanceOf[Shape]){
      println(3)
      ComposedShape(List(this) ::: List(s))
    }
    else{
      println(4)
      ComposedShape(List(this) ::: s.asInstanceOf[ComposedShape[A]].l)
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

case class ComposedShape[T <: Shape](var l: List[T]) extends Shape {

  type A = T

  def size():Int={
    l.size
  }

  def removeLast():Unit={
    l = l.take(size()-1)
  }

  def addFirst(shape:T):Unit={
    l = List(shape) ++ l
  }

  def map(f: Shape => Shape): ComposedShape[Shape] = {
    ComposedShape(l.map(f).asInstanceOf[List[Shape]])
  }

  def flatMap(f: Shape => Iterable[Shape]): ComposedShape[Shape] = {
    ComposedShape(l.flatMap(f).asInstanceOf[List[Shape]])
  }

  def foreach[B](f: Shape => B): Unit = {
    l.map(f)
  }

  def apply(i: Int): T = {
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

  def ++(composedShape: ComposedShape[Shape]): ComposedShape[Shape] = {
    ComposedShape(l ++ composedShape.l)
  }

  override def and(s: Shape): ComposedShape[Shape] = {
    if (s.isInstanceOf[ComposedShape[A]]) {
      ComposedShape(l ::: s.asInstanceOf[ComposedShape[A]].l)
    }
    else if (s.isInstanceOf[A]) {
      ComposedShape(l ::: List(s))
    }
    else if(s.isInstanceOf[Shape]){
      ComposedShape(l::: List(s))
    }
    else{
      ComposedShape(l ::: s.asInstanceOf[ComposedShape[A]].l)
    }
  }

}

case class Rectangle(var x: Int, var y: Int, var width: Int, var height: Int) extends Shape with ShapeAttributes {
  type  A = Rectangle

  override def moveX(v: Int): Unit = {
    x += v
  }

  override def moveY(v: Int): Unit = {
    y += v
  }

  override def change(property: CanvasElementModifier[A]): Unit = {
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

  override def change(property: CanvasElementModifier[A]): Unit = {
    property.change(this)
  }
}

case class Square(var x: Int, var y: Int, var side: Int) extends Shape with ShapeAttributes {
  type A = Square

  override def moveX(v: Int): Unit = {
    x += v
  }

  override def moveY(v: Int): Unit = {
    y += v
  }

  override def change(property: CanvasElementModifier[A]): Unit = {
    property.change(this)
  }
}

object ComposedShapeImplicits {

  implicit def arrayCircleToComposedShape(a: Array[Circle]) = ComposedShape(a.toList)

  implicit def arrayRectangleToComposedShape(a: Array[Rectangle]) = ComposedShape(a.toList)

  implicit def arrayShapeToComposedShape(a: Array[Square]) = ComposedShape(a.toList)

  implicit def arrayShapeToComposedShape(a: Array[Shape]) = ComposedShape(a.toList)


}
