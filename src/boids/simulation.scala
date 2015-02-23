package boids

object Simulation extends Runnable {

  val size = 100 // Number of boids
  val mass = 100 // Mass of one boid
  val boidDistance = 4000 // Max distance that boids affect each others
  val boidMaxForce = 5 // Max length for the acceleration vector
  val boidMaxSpeed = 1 // Max speed that boid moves in one simulation step

  var flock = Vector.fill[Boid](size)(Boid.generateBoid).par
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