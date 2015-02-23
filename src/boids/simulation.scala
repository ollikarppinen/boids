package boids

object Simulation extends Runnable {
  val size = 1000 // Number of boids
  var flock = Vector.fill[Boid](size)(Boid.doBoid).par // Parallel collection of boids.
  def run(): Unit = {
    while (true) { // simple simulation loop
      val t = System.currentTimeMillis()
      flock = flock.map(_.move)
      SimulationPanel.repaint()
      val w = t + 50 - System.currentTimeMillis()
      Thread.sleep(if (w > 0) w else 0)
    }
  }
}