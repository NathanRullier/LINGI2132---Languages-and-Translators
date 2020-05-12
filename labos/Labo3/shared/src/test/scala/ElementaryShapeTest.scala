import DSL._

import common.Common._

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class ElementaryShapeTest extends AnyFunSuite with Matchers {

  Random.setSeed(183945)

  test("Move rectangle up") {
    val offSet = Random.nextInt(20)
    val rectangle = newRectangle()
    val expected = rectangle.y - offSet
    rectangle moveY(-offSet)
    rectangle.y shouldBe expected
  }

  test("Move circle left") {
    val offSet = Random.nextInt(20)
    val circle = newCircle()
    val expected = circle.x - offSet
    circle moveX(-offSet)
    circle.x shouldBe expected
  }

  test("Change color of rectangle via keyword") {
    val rectangle = newRectangle()
    val newColor = s"rgb ${Random.nextInt(255)} ${Random.nextInt(255)} ${Random.nextInt(255)}"
    rectangle color newColor
    rectangle.color shouldBe newColor
  }

  test("Change color of circle via property") {
    val circle = newCircle()
    val newColor = s"rgb ${Random.nextInt(255)} ${Random.nextInt(255)} ${Random.nextInt(255)}"
    circle change Color(newColor)
    circle.color shouldBe newColor
  }

  test("Change radius of circle via property") {
    val circle = newCircle()
    val newRadius = Random.nextInt(100)
    circle change Radius(newRadius)
    circle.radius shouldBe newRadius
  }
  test("Change width of rectangle via property") {
    val rectangle = newRectangle()
    val newWidth = Random.nextInt(100)
    rectangle change Width(newWidth)
    rectangle.width shouldBe newWidth
  }
  test("Change height of rectangle via property") {
    val rectangle = newRectangle()
    val newHeight = Random.nextInt(100)
    rectangle change Height(newHeight)
    rectangle.height shouldBe newHeight
  }

}
