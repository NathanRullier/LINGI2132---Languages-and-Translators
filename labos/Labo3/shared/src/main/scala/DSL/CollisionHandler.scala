package DSL

import DSL._

object CollisionHandler {

  def perfectCollisionObjxObj(obj1X: Int, obj1Y: Int,obj2X: Int,obj2Y: Int): Boolean = {
    if((obj1X == obj2X) && (obj1Y == obj2Y)) true
    else false
  }
  def collisionObjxBorders(objX: Int, objY: Int, borderMinX: Int, borderMinY: Int, borderMaxX: Int, borderMaxY: Int): Boolean ={
    if((objX < borderMinX) || (objX >= borderMaxX) || (objY < borderMinY) || (objY >= borderMaxY)) true
    else false
  }
}