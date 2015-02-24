package boids

object Simulation extends Runnable {
  def generateFlock(i: Int) = Vector.fill[Boid](i)(Boid.doBoid).par
  def restart() = flock = generateFlock(flock.size)
  var size = 200
  var flock = generateFlock(size) // Parallel collection of boids.
  private var running = true // A flag to run the simulation loop
  def pause() = {
    if (running) {
      running = false
    } else {
      running = true
      this.notifyAll()
    }
  }
  def run(): Unit = {
    while (true) { // simulation loop
      val t = System.currentTimeMillis()
      flock = flock.map(_.move)
      SimulationPanel.repaint()
      val w = t + 40 - System.currentTimeMillis()
      if (flock.size != size) { // Checks that if size has been changed (through settings) and then resizes flock
        if (flock.size < size) {
          flock = flock ++ generateFlock(size - flock.size)
        } else {
          flock = flock.take(size)
        }
      }
      Thread.sleep(if (w > 0) w else 0)
    }
  }
}