package boids

import scala.util.Random

object Boid {
  val mass = 100 // Mass of one boid
  val distance = 1000 // Max distance that boids affect each others
  val maxForce = 5 // Max length for the acceleration vector
  val maxSpeed = 2 // Max speed that boid moves in one simulation step
  val r = new Random
  def doBoid: Boid = {
    new Boid(Vector2D(r.nextInt(501),
      r.nextInt(501)),
      Vector2D(r.nextInt(3) + r.nextFloat() - 2, r.nextInt(3) + r.nextFloat() - 2))
  }
}

class Boid(val position: Vector2D, val velocity: Vector2D) {
  def move: Boid = {
    val nearbyBoids = Simulation.flock.filter(b => this.position - position.transfix(b.position) < Boid.distance && b != this)
    if (nearbyBoids.size > 0) {
      val separation = nearbyBoids.map(b => (this.position - position.transfix(b.position)) * this.position.inverseDistance(b.position)).reduce(_ + _)
      val cohesion = nearbyBoids.map(b => position.transfix(b.position) / nearbyBoids.size).reduce(_ + _) - this.position
      val alignment = nearbyBoids.map(_.velocity / nearbyBoids.size).reduce(_ + _)
      val acceleration = (separation + cohesion + alignment).truncate(Boid.maxForce) / Boid.mass
      val newVelocity = (velocity + acceleration).truncate(Boid.maxSpeed)
      val newPosition = (position + newVelocity).bound
      new Boid(newPosition, newVelocity)
    } else {
      new Boid((position + velocity).bound, velocity)
    }
  }
}