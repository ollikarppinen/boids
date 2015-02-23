package boids

import scala.util.Random

object Simulation extends Runnable {
  val r = new Random
  var flock = Vector.fill[Boid](100)(new Boid(Vector(r.nextInt(501), r.nextInt(501)), Vector(r.nextInt(3) + r.nextFloat() - 2, r.nextInt(3) + r.nextFloat() - 2), 0)).par
  def run(): Unit = {
    while (true) { // simple simulation loop
      flock = flock.map(_.move)
      SimulationPanel.repaint()
    }
  }

}