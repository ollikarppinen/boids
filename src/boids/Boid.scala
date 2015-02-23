package boids

import scala.math._
import scala.util.Random

object Boid {
  val distance = Simulation.boidDistance
  val mass = Simulation.boidMass // Inverse of mass
  val maxForce = Simulation.boidMaxForce
  val maxSpeed = Simulation.boidMaxSpeed
  val r = new Random
  def generateBoid: Boid = {
    new Boid(Vector2D(r.nextInt(501),
      r.nextInt(501)),
      Vector2D(r.nextInt(3) + r.nextFloat() - 2, r.nextInt(3) + r.nextFloat() - 2))
  }
}

class Boid(val position: Vector2D, val velocity: Vector2D) {
  def move: Boid = {
    val nearbyBoids = Simulation.flock.filter(b => this.position - b.position < Boid.distance && b != this)
    if (nearbyBoids.size > 0) {
      val separation = nearbyBoids.map(b => (this.position - b.position) * this.position.inverseDistance(b.position)).reduce(_ + _)
      val cohesion = this.position - nearbyBoids.map(_.position * (1f / nearbyBoids.size)).reduce(_ + _)
      val alignment = nearbyBoids.map(_.velocity * (1f / nearbyBoids.size)).reduce(_ + _)
      val acceleration = (separation * Simulation.s + cohesion * Simulation.c + alignment * Simulation.a).truncate(Boid.maxForce) * Boid.mass
      val newVelocity = (velocity + acceleration).truncate(Boid.maxSpeed)
      val newPosition = (position + newVelocity).bound
      println("separation: " + separation)
      println("cohesion: " + cohesion)
      println("alignment: " + alignment)
      new Boid(newPosition, newVelocity)
    } else {
      new Boid((position + velocity).bound, velocity)
    }
  }
}