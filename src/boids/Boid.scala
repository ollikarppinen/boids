package boids

import scala.util.Random

object Boid {
  var mass = 10 // Mass of one boid
  var distance = 900 // Max distance squared that boids affect each others
  var maxForce = 3f // Max length for the acceleration vector
  var maxSpeed = 2f // Max speed that boid moves in one simulation step
  val r = new Random
  def doBoid: Boid = {
    new Boid(Vector2D(r.nextInt(501),
      r.nextInt(501)),
      Vector2D(r.nextInt(2) + r.nextFloat() - 1, r.nextInt(2) + r.nextFloat() - 1))
  }
}

class Boid(val position: Vector2D, val velocity: Vector2D) {
  def move: Boid = {
    val nearbyBoids = Simulation.flock.filter(b => this.position - position.transfix(b.position) < Boid.distance)
    val separation = nearbyBoids.map(b => (this.position - position.transfix(b.position)) * this.position.inverseDistance(b.position)).reduce(_ + _)
    val cohesion = (nearbyBoids.map(b => (position.transfix(b.position) / nearbyBoids.size)).reduce(_ + _) - this.position)
    .truncate(Boid.maxForce) // This is a little bit shady way of dealing with too influencial cohesion vectors
    val alignment = nearbyBoids.map(_.velocity / nearbyBoids.size).reduce(_ + _)
    val acceleration = (separation + cohesion + alignment).truncate(Boid.maxForce) / Boid.mass
    val newVelocity = (velocity + acceleration).truncate(Boid.maxSpeed)
    val newPosition = (position + newVelocity).bound
    new Boid(newPosition, newVelocity)
  }
}