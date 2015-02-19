package boids

import scala.util.Random

object simulation extends Runnable {
	val r = new Random
	val flock = Vector.fill[Boid](10)(new Boid(Vector(r.nextInt(501), r.nextInt(501)), Vector(1)))

  def run(): Unit = {
    while (true) {
      val start = System.currentTimeMillis()
      flock.foreach(_.move)
      SimulationPanel.repaint()
      Thread.sleep(start + 5 - System.currentTimeMillis())
    }
  }

}