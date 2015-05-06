package boids

import scala.swing.Panel
import java.awt.{ Graphics2D, Color, Dimension, RenderingHints }

object SimulationPanel extends Panel {  // The panel where the simulation is drawn. 
  preferredSize = new Dimension(1000, 1000)
  opaque = true
  background = new Color(80, 180, 235)
  
  override def paintComponent(g: Graphics2D) {  // The method which handles the drawing of the boids.
    super.paintComponent(g)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) // AA is turned on.
    def drawBoid(b: Boid) = {                  // Method which draws a boid b in the simulation panel.
      g.setColor(Color.white)                  // Sets the color of a boid.
      // These are the vectors that are used to define the corner coordinates of the boid polygon.
      val polygonVector = b.velocity.normalize * 5
      val inversePolygonVector = polygonVector * - 1
      val scaledPosition = b.position * 2
      val polygonVectors = Vector(scaledPosition + polygonVector, 
                                  scaledPosition + inversePolygonVector + inversePolygonVector.rotate, 
                                  scaledPosition, 
                                  scaledPosition + inversePolygonVector - inversePolygonVector.rotate)
      val x = polygonVectors.map(i => math.max(i.x, 0).toInt).toArray // A collection of x coordinates that create the boid polygon.
      val y = polygonVectors.map(i => math.max(i.y, 0).toInt).toArray // Same as above but for y coordinates.
      g.fillPolygon(x, y, 4)  // Draws a filled polygon which represents singular boid.
    }
    Simulation.flock.foreach(b => drawBoid(b))  // Each boid is drawn in the panel.
  }
  var t = new Thread(Simulation)  // Creates the simulation thread when program is first opened.
  t.start()             // Starts the simulation thread when this program is first opened.
  var running = true    // A flag to see if simulation thread is running.
  
  def pause() = {       // A method that pauses the simulation thread if it's running, or runs the simulation thread if it's not running.
    if (running) {
      running = false
      t.stop()
    } else {
      running = true
      t = new Thread(Simulation)
      t.start()
    }
  }
  def restart() = {      // Method that creates a new flock with random positions with the current settings.
    if (running) pause() // This makes sure that the simulation isn't runnign while the new flock is generated.
    Simulation.flock = Simulation.generateFlock(Simulation.size)  // Creates the new flock.
    pause()              // Puts the simulation thread running.
  }
}