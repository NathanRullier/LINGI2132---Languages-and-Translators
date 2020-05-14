package DSL

/**
 * Shape trait, kind of an abstract class
 */
sealed trait Shape {
  type A <: Shape

  /**
   * This method return a coposedShape depending on the instance of the Shape in parameter
   *
   * @param s a shape
   * @return A list of shapes
   */
  def and(s: Shape): ComposedShape[Shape] = {
    if (s.isInstanceOf[ComposedShape[A]]) {
      println(1)
      ComposedShape(List(this) ::: s.asInstanceOf[ComposedShape[A]].l)
    }
    else if (s.isInstanceOf[A]) {
      println(2)
      ComposedShape[Shape](List(this) ::: List(s))
    }
    else if (s.isInstanceOf[Shape]) {
      println(3)
      ComposedShape(List(this) ::: List(s))
    }
    else {
      println(4)
      ComposedShape(List(this) ::: s.asInstanceOf[ComposedShape[A]].l)
    }
  }

  /**
   *
   * @param property the canvas element modifier on a target object
   */
  def change(property: CanvasElementModifier[A]): Unit = ???

  /**
   *
   * @param v value to move in x
   */
  def moveX(v: Int): Unit = ???

  /**
   *
   * @param v value to move in y
   */
  def moveY(v: Int): Unit = ???
}

/**
 * Contains the attributes of a shape : the color and the strok width
 */
sealed trait ShapeAttributes {
  var color: String = "red"

  def color(s: String): Unit = {
    color = s
  }

  var strokeWidth: Int = 1

  /**
   *
   * @param i the chosent stroke width
   */
  def strokeWidth(i: Int): Unit = {
    strokeWidth = i
  }
}

/**
 * case class that Handles a group of different shape
 * @param l List containing  lists of shapes
 * @tparam T Type of shape
 */
case class ComposedShape[T <: Shape](var l: List[T]) extends Shape {

  type A = T

  /**
   * get size of the list composedShape
   * @return Int list size
   */
  def size(): Int = {
    l.size
  }

  /**
   * Method that remove the last element of the composed shape list
   */
  def removeLast(): Unit = {
    l = l.take(size() - 1)
  }

  /**
   * Add a shape at the first position of the composed shape list
   * @param shape The shape to add
   */
  def addFirst(shape: T): Unit = {
    l = List(shape) ++ l
  }

  /**
   * Add a shape at the last position of the composed shape list
   * @param shape The shape to add
   */
  def addLast(shape: T): Unit = {
    l = l ++ List(shape)
  }

  /**
   * Monad, maps the composed shape
   *
   * @param function that returns a shape
   * @return the list of the composed shapes
   */
  def map(f: Shape => Shape): ComposedShape[Shape] = {
    ComposedShape(l.map(f).asInstanceOf[List[Shape]])
  }
  /**
   * Monad, Flatmaps the composed shape
   *
   * @param function that returns a shape
   * @return the list of the composed shapes
   */
  def flatMap(f: Shape => Iterable[Shape]): ComposedShape[Shape] = {
    ComposedShape(l.flatMap(f).asInstanceOf[List[Shape]])
  }

  /**
   * Foreach definition for the composed shape list
   * @param f shape temp used for the foreach
   * @tparam B content of the funntion
   */
  def foreach[B](f: Shape => B): Unit = {
    l.map(f)
  }

  /**
   *
   * @param index
   * @return the composed shape list at the index l
   */
  def apply(i: Int): T = {
    l(i)
  }

  /**
   *
   * @param v value to move in x
   */
  override def moveX(v: Int): Unit = {
    l.map(x => x.moveX(v))
  }

  /**
   *
   * @param v value to move in y
   */
  override def moveY(v: Int): Unit = {
    l.map(x => x.moveY(v))
  }

  /**
   *
   * @param property the canvas element modifier on a target object
   */
  override def change(property: CanvasElementModifier[A]): Unit = {
    l.map(x => x.change(property.asInstanceOf[CanvasElementModifier[x.A]]))
  }

  /**
   *
   * @param composedShape is a composeedShape we are adding to the main composedshape list
   * @return the composedShape list containing the new composedShape
   */
  def ++(composedShape: ComposedShape[Shape]): ComposedShape[Shape] = {
    ComposedShape(l ++ composedShape.l)
  }

  /**
   *
   * @param s a shape
   * @return A list of shapes
   */
  override def and(s: Shape): ComposedShape[Shape] = {
    if (s.isInstanceOf[ComposedShape[A]]) {
      ComposedShape(l ::: s.asInstanceOf[ComposedShape[A]].l)
    }
    else if (s.isInstanceOf[A]) {
      ComposedShape(l ::: List(s))
    }
    else if (s.isInstanceOf[Shape]) {
      ComposedShape(l ::: List(s))
    }
    else {
      ComposedShape(l ::: s.asInstanceOf[ComposedShape[A]].l)
    }
  }

}

/**
 * Child of the class Shape with shape attributes, A basic rectangle
 * @param x position x
 * @param y position y
 * @param width width of the rectangle
 * @param height height of the rectangle
 */
case class Rectangle(var x: Int, var y: Int, var width: Int, var height: Int) extends Shape with ShapeAttributes {
  type A = Rectangle

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

/**
 * Child of the class shape with shape attributes, A basic circle
 * @param x position x
 * @param y position y
 * @param radius radius of the circle
 */
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

/**
 * Child of the class Shape with shape attributes, A basic square
 * @param x position x
 * @param y position y
 * @param side length of a side of the square
 */
case class Square(var x: Int, var y: Int, var side: Int) extends Shape

    with ShapeAttributes {
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
