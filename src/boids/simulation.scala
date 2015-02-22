package boids

import scala.util.Random

object simulation extends Runnable {
  val r = new Random
  var flock = Vector.fill[Boid](100)(new Boid(Vector(r.nextInt(501), r.nextInt(501)), Vector(r.nextInt(3) + r.nextFloat() - 2, r.nextInt(3) + r.nextFloat() - 2)))
  def run(): Unit = {
    var i = 0
    var t = 0.0
    while (true) { // simple simulation loop
      i += 1
      val q = System.currentTimeMillis()
      flock = flock.map(_.move)
      t += System.currentTimeMillis() - q
      println(t / i)
      SimulationPanel.repaint()
    }
  }

}