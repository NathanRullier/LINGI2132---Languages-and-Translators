package DSL

import DSL._

object CollisionHandler {
  val getXOfShape = (obj: Shape) => {
    obj match {
      case circle: Circle => circle.x
      case rectangle: Rectangle => rectangle.x
      case square: Square => square.x

    }
  }
  val getYOfShape = (obj: Shape) => {
    obj match {
      case circle: Circle => circle.y
      case rectangle: Rectangle => rectangle.y
      case square: Square => square.y

    }
  }

  val getHeightOfShape = (obj: Shape) => {
    obj match {
      case circle: Circle => circle.radius
      case rectangle: Rectangle => rectangle.height
      case square: Square => square.side

    }
  }

  val getWidthOfShape = (obj: Shape) => {
    obj match {
      case circle: Circle => circle.radius
      case rectangle: Rectangle => rectangle.width
      case square: Square => square.side

    }
  }

  def perfectCollisionObjxObj(obj1: Shape, obj2: Shape): Boolean = {

    if ((getXOfShape(obj1) == getXOfShape(obj2)) && (getYOfShape(obj1) == getYOfShape(obj2))) true
    else false
  }

  def perfectCollisionObjxObj(obj1X: Int, obj1Y: Int, obj2X: Int, obj2Y: Int): Boolean = {
    if ((obj1X == obj2X) && (obj1Y == obj2Y)) true
    else false
  }

  def collisionObjxBorders(objX: Int, objY: Int, borderMinX: Int, borderMinY: Int, borderMaxX: Int, borderMaxY: Int): Boolean = {
    if ((objX < borderMinX) || (objX >= borderMaxX) || (objY < borderMinY) || (objY >= borderMaxY)) true
    else false
  }

  def collisionObjxBorders(objContained: Shape, objBorder: Shape): Boolean = {
    val xBorder = getXOfShape(objBorder)
    val yBorder = getYOfShape(objBorder)
    if ((getXOfShape(objContained) < xBorder) ||
      (getXOfShape(objContained) >= getWidthOfShape(objBorder) + xBorder) ||
      (getYOfShape(objContained) < yBorder) ||
      (getYOfShape(objContained) >= getHeightOfShape(objBorder) + yBorder)) true
    else false
  }
}