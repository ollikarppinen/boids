package boids

import scala.swing.Panel
import java.awt.{ Graphics2D, Color, Dimension }

object SimulationPanel extends Panel {
  preferredSize = new Dimension(500, 500)
  opaque = true
  background = Color.black
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    def drawBoid(b: Boid) = {
      g.setColor(Color.white)
      g.drawOval(b.position(0).toInt, b.position(1).toInt, 1, 1)
    }
    simulation.flock.foreach(b => drawBoid(b))
  }
  new Thread(simulation).start()
}