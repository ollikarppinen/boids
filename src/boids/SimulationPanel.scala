package boids

import scala.swing.Panel
import java.awt.{ Graphics2D, Color, Dimension, RenderingHints }

object SimulationPanel extends Panel {
  preferredSize = new Dimension(1000, 1000)
  opaque = true
  background = new Color(80, 180, 235)
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    def drawBoid(b: Boid) = {
      g.setColor(Color.white)
      val polygonVector = b.velocity.normalize * 5
      val inversePolygonVector = polygonVector * - 1
      val scaledPosition = b.position * 2
      val polygonVectors = Vector(scaledPosition + polygonVector, 
                                  scaledPosition + inversePolygonVector + inversePolygonVector.rotate, 
                                  scaledPosition, 
                                  scaledPosition + inversePolygonVector - inversePolygonVector.rotate)
      val x = polygonVectors.map(i => math.max(i.x, 0).toInt).toArray
      val y = polygonVectors.map(i => math.max(i.y, 0).toInt).toArray
      g.fillPolygon(x, y, 4)
    }
    Simulation.flock.foreach(b => drawBoid(b))
  }
  var t = new Thread(Simulation)
  t.start()
  var running = true
  def pause() = {
    if (running) {
      running = false
      t.stop()
    } else {
      running = true
      t = new Thread(Simulation)
      t.start()
    }
  }
  def restart() = {
    if (running) pause()
    Simulation.flock = Simulation.generateFlock(Simulation.size)
    if (!running) pause()
  }
}