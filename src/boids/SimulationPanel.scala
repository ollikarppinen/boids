package boids

import scala.swing.Panel
import java.awt.{ Graphics2D, Color, Dimension }

object SimulationPanel extends Panel {
  preferredSize = new Dimension(501, 501)
  opaque = true
  background = Color.black
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    g.setColor(Color.darkGray)

    for (x <- 0 to 50; y <- 0 to 50) { // draws the grid
      g.drawLine(0, x * 10, 500, x * 10)
      g.drawLine(x * 10, 0, x * 10, 500)
    }

    def drawBoid(b: Boid) = {
    	g.setColor(Color.white)
      g.drawOval(b.position(0).toInt - 6, b.position(1).toInt - 6, 5, 5)   
    }
    simulation.flock.foreach(b => drawBoid(b))
  }
  new Thread(simulation).start()
}