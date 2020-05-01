package DSLDemo

import org.scalajs.dom.html

class Canvasy(canvas: html.Canvas) {
    private var shapes = Array[Shape]()
    def draw(): Unit = {}
    def += (circles : Array[Circle] ) { shapes = shapes ++ circles }
    def += (rectangles : Array[Rectangle] ) { shapes = shapes ++ rectangles }
}
