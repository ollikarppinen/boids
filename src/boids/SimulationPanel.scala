package boids

import scala.swing.Panel
import java.awt.{ Graphics2D, Color, Dimension, RenderingHints }

object SimulationPanel extends Panel {
  preferredSize = new Dimension(500, 500)
  opaque = true
  background = Color.black
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    def drawBoid(b: Boid) = {
      g.setColor(Color.white)
      g.drawOval(b.position.x.toInt, b.position.y.toInt, 1, 1)
    }
    Simulation.flock.foreach(b => drawBoid(b))
  }
  new Thread(Simulation).start()
}