package boids

import scala.math._

class Boid(val position: Vector[Float], val velocity: Vector[Float]) {
  val mass = 10
  val maxForce = 5
  val maxSpeed = 2
  def getX = position(0).toInt
  def getY = position(1).toInt
  def distance(a: Vector[Float], b: Vector[Float]): Float = sqrt(pow(a(0) - b(0), 2) + pow(a(1) - b(1), 2)).toFloat
  def normalize(v: Vector[Float]) = v.map(_ / distance(v, Vector(0, 0))) //normalizes a Vector. Does not handle 0 distance. !!!
  def substract(a: Vector[Float], b: Vector[Float]) = Vector(a(0) - b(0), a(1) - b(1))
  def sum(a: Vector[Float], b: Vector[Float]): Vector[Float] = Vector(a(0) + b(0), a(1) + b(1))
  def truncate(a: Vector[Float], d: Float) = if (distance(a, Vector(0, 0)) > d) normalize(a).map(_ * d) else a

  def move: Boid = {
    val nearbyBoids: Vector[Boid] = simulation.flock.filter(b => distance(this.position, b.position) < 10 && distance(this.position, b.position) > 0) // this is bad. FIX THIS!
    if (nearbyBoids.size > 0) {
      val separationVector = nearbyBoids.map(b => normalize(substract(this.position, b.position)).map(_ / distance(this.position, b.position))).flatten
      val cohesionVector = nearbyBoids.map(_.position).fold(Vector[Float](0, 0))(sum(_, _)).map(_ / nearbyBoids.size)
      val alignmentVector = nearbyBoids.map(_.velocity).fold(Vector[Float](0, 0))(sum(_, _)).map(_ / nearbyBoids.size)
      val steeringDirection = sum(sum(separationVector, cohesionVector), alignmentVector)
      val steeringForce = truncate(steeringDirection, maxForce)
      val acceleration = steeringForce.map(_ / mass)
      val newVelocity = truncate(sum(velocity, acceleration), maxSpeed)
      val newPosition = sum(position, newVelocity).map(_ % 500)
      new Boid(newPosition, newVelocity)
    } else {
      new Boid(sum(position, velocity), velocity)
    }
  }
}