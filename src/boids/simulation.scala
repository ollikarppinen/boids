package boids

import scala.util.Random

object simulation extends Runnable {
	val r = new Random
	var flock = Vector.fill[Boid](50)(new Boid(Vector(r.nextInt(501), r.nextInt(501)), Vector( r.nextInt(3) + r.nextFloat() - 2,  r.nextInt(3) + r.nextFloat() - 2)))
  def run(): Unit = {
    while (true) {
      val start = System.currentTimeMillis()
      flock = flock.map(_.move)
      SimulationPanel.repaint()
//      Thread.sleep(start + 100 - System.currentTimeMillis())
    }
  }

}