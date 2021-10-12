
import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import javax.swing.{JFrame, WindowConstants}

object Main {
  def draw(g: Graphics2D): Unit = {
    val img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB)
    Render.renderTriangleAround(img,300,300,1)
    g.drawImage(img, 0, 0, null)
  }

  def main(args: Array[String]): Unit = {
    val jf = new JFrame()
    jf.setSize(1000, 1000) //размер экрана

    jf.setUndecorated(false) //показать заголовок окна

    jf.setTitle("Моя супер программа")
    jf.setVisible(true)
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    jf.createBufferStrategy(2)
    //в бесконечном цикле рисуем новый кадр
    while (true) {
      val frameLength = 1000 / 60 //пытаемся работать из рассчета  60 кадров в секунду
      val start = System.currentTimeMillis
      val bs = jf.getBufferStrategy
      val g = bs.getDrawGraphics.asInstanceOf[Graphics2D]
      g.clearRect(0, 0, jf.getWidth, jf.getHeight)
      draw(g)
      bs.show()
      g.dispose()
      val `end` = System.currentTimeMillis
      val len = `end` - start
      if (len < frameLength) Thread.sleep(frameLength - len)
    }
  }

}
