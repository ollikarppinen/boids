package boids

object Simulation extends Runnable {
  val size = 100 // number of boids
  val mass = 10
  val boidDistance = 10000
  val boidMass = 1f / mass
  val boidMaxForce = 5
  val boidMaxSpeed = 1

  // Weights for the different rules
  val s = 1.0f
  val c = 1.0f
  val a = 1.0f

  var flock = Vector.fill[Boid](size)(Boid.generateBoid).par
  def run(): Unit = {
    while (true) { // simple simulation loop
      flock = flock.map(_.move)
      SimulationPanel.repaint()
    }
  }

}