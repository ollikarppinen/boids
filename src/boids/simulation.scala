package boids

import scala.util.Random

object Simulation extends Runnable {
	val size = 100 // number of boids
  val boidDistance = 100
  val boidMass = 10
  val boidMaxForce = 5
  val boidMaxSpeed = 1
  
  val r = new Random
  var flock = Vector.fill[Boid](size)(new Boid(Vector(r.nextInt(501), r.nextInt(501)), Vector(r.nextInt(3) + r.nextFloat() - 2, r.nextInt(3) + r.nextFloat() - 2))).par
  def run(): Unit = {
    while (true) { // simple simulation loop
      flock = flock.map(_.move)
      SimulationPanel.repaint()
    }
  }

}