import java.awt.image.BufferedImage
import java.awt.{Color, Point}


object Render {

  def renderLine(img: BufferedImage, x1: Double, y1: Double, x2: Double, y2: Double, color: Int): Unit = {
    val k = AfinLineK(x1, y1, x2, y2)
    val b = AfinLineB(x1, y1, k)
    if (x1 == x2) {
      if (y1 < y2) {
        for (i <- y1.toInt until y2.toInt) {
          img.setRGB(x1.toInt, i, new Color(color).getRGB)

        }
      }
      else if (y1 > y2) {
        for (i <- y2.toInt until y1.toInt) {
          img.setRGB(x1.toInt, i, new Color(color).getRGB)
        }
      }
    }
    else if (y1 == y2) {
      if (x1 < x2) {
        for (i <- x1.toInt until x2.toInt) {
          img.setRGB(i, y1.toInt, new Color(color).getRGB)

        }
      }
      else if (x1 > x2) {
        for (i <- x2.toInt until x1.toInt) {
          img.setRGB(i, y1.toInt, new Color(color).getRGB)
        }
      }
    }
    else if (math.abs(x1 - x2) >= math.abs(y1 - y2)) {
      if (x1 < x2) {
        for (i <- x1.toInt until x2.toInt) {
          var y = k * i + b
          img.setRGB(i, y.toInt, new Color(color).getRGB)
        }
      }
      else if (x1 > x2) {
        for (i <- x2.toInt until x1.toInt) {
          var y = k * i + b
          img.setRGB(i, y.toInt, new Color(color).getRGB)
        }
      }
    }
    else {
      if (y1 < y2) {
        for (i <- y1.toInt until y2.toInt) {
          var x = (i - b) / k
          img.setRGB(x.toInt, i, new Color(color).getRGB)
        }
      }
      if (y1 > y2) {
        for (i <- y2.toInt until y1.toInt) {
          var x = (i - b) / k
          img.setRGB(x.toInt, i, new Color(color).getRGB)
        }
      }
    }
  }

  def renderTriangle(img: BufferedImage, x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double, color: Int): Unit = {
    var ArrayRect = rect(x1, y1, x2, y2, x3, y3)
    for (i <- Math.min(x1, Math.min(x2, x3)).toInt until Math.max(x1, Math.max(x2, x3)).toInt) {
      for (j <- Math.min(y1, Math.min(y2, y3)).toInt until Math.max(y1, Math.max(y2, y3)).toInt) {
        val ax = x2 - x1
        val bx = x3 - x1
        val cx = (i - x1)
        val ay = y2 - y1
        val by = y3 - y1
        val cy = (j - y1)
        val detT = ax * by - ay * bx
        val detu = (cx) * by - cy * bx
        val detv = ax * (cy) - cx * ay
        val u = detu / detT
        val v = detv / detT
        if(0.0<=v&&0.0<=u&&u+v<=1){
          img.setRGB(i,j,new Color(u.toFloat,v.toFloat,1-u.toFloat-v.toFloat).getRGB)
        }

      }
    }
  }

  def renderTriangleAround(img: BufferedImage, x1: Double, y1: Double, color: Int): Unit = {
    for(i<-1 until 32){
      def a(i: Int) = (2*Math.PI*i)/20
      renderTriangle(img,x1,y1,x1+Math.sin(a(i))*100,y1+Math.cos(a(i))*100,x1+Math.sin(a(i+1))*100,y1+Math.cos(a(i+1))*100, color )
    }
  }

  def rect(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double): Array[Point] = {
    var ArrayRect = Array.ofDim[Point](4)
    ArrayRect(0) = new Point(math.min(x3.toInt, math.min(x1.toInt, x2.toInt)), math.max(y3.toInt, math.max(y1.toInt, y2.toInt)))
    ArrayRect(1) = new Point(math.min(x3.toInt, math.min(x1.toInt, x2.toInt)), math.min(y3.toInt, math.min(y1.toInt, y2.toInt)))
    ArrayRect(2) = new Point(math.max(x3.toInt, math.max(x1.toInt, x2.toInt)), math.min(y3.toInt, math.min(y1.toInt, y2.toInt)))
    ArrayRect(3) = new Point(math.max(x3.toInt, math.max(x1.toInt, x2.toInt)), math.max(y3.toInt, math.max(y1.toInt, y2.toInt)))
    ArrayRect
  }

  def AfinLineK(x1: Double, y1: Double, x2: Double, y2: Double): Double = (y1 - y2) / (x1 - x2)

  def AfinLineB(x1: Double, y1: Double, k: Double): Double = y1 - (k * x1)

}
