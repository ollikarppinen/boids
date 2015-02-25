package boids

object Simulation extends Runnable {
  var size = 200
  var flock = generateFlock(size) // Parallel collection of boids.
  def generateFlock(i: Int) = Vector.fill[Boid](i)(Boid.doBoid).par
  def run(): Unit = {
    while (true) { // simulation loop
      val t = System.currentTimeMillis()
      flock = flock.map(_.move)
      val w = t + 30 - System.currentTimeMillis()
      if (flock.size != size) { // Checks that if size has been changed (through settings) and then resizes flock
        if (flock.size < size) {
          flock = flock ++ generateFlock(size - flock.size)
        } else {
          flock = flock.take(size)
        }
      }
      Thread.sleep(if (w > 0) w else 0)
      SimulationPanel.repaint()
    }
  }
}