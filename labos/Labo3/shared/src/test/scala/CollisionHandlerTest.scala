import DSL._

import common.Common._

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class CollisionHandlerTest extends AnyFunSuite with Matchers {

  test("perfectCollisionObjxObj") {

    val c1 = Circle(0, 0, 20)
    val c2 = Circle(0, 0, 20)
    CollisionHandler.perfectCollisionObjxObj(c1,c2) shouldBe true

  }
  test("perfectCollisionObjxObj with positions") {
    CollisionHandler.perfectCollisionObjxObj(1,2,2,1) shouldBe false
  }

  test("collisionObjxBorders with the position of the borders and position of the object") {
    CollisionHandler.collisionObjxBorders(5,5,0,0,10,10) shouldBe false
  }

  test("collisionObjxBorders") {
    val objBorder = Circle(10, 10, 20)
    val obj = Circle(5, 5, 20)
    CollisionHandler.collisionObjxBorders(obj, objBorder) shouldBe true
  }
}