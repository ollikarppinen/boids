package boids

import scala.math._

class Boid(val position: Vector[Float], val velocity: Vector[Float]) {
  val mass = 10
  val maxForce = 5
  val maxSpeed = 3
  def distance(a: Vector[Float], b: Vector[Float]): Float = sqrt(pow(a(0) - b(0), 2) + pow(a(1) - b(1), 2)).toFloat
  def normalize(v: Vector[Float]) = v.map(_ / distance(v, Vector(0, 0))) //normalizes a Vector. Does not handle 0 distance. !!!
  def substract(a: Vector[Float], b: Vector[Float]) = Vector(a(0) - b(0), a(1) - b(1))
  def sum(a: Vector[Float], b: Vector[Float]): Vector[Float] = Vector(a(0) + b(0), a(1) + b(1))
  def truncate(a: Vector[Float], d: Float) = if (distance(a, Vector(0, 0)) > d) normalize(a).map(_ * d) else a
  def multiply(a: Vector[Float], m: Float) = Vector(a(0) * m, a(1) * m)

  def move: Boid = {
    val nearbyBoids: Vector[Boid] = simulation.flock.filter(b => distance(this.position, b.position) < 10 && distance(this.position, b.position) > 0) // this is bad. FIX THIS!
    if (nearbyBoids.size > 0) {
      val separationVector = nearbyBoids.map(b => multiply(normalize(substract(this.position, b.position)), 10 / distance(this.position, b.position))).fold(Vector[Float](0, 0))(sum(_, _))
      val cohesionVector = substract(nearbyBoids.map(b => multiply(b.position, 1.toFloat / nearbyBoids.size)).fold(Vector[Float](0, 0))(sum(_, _)), this.position)
      val alignmentVector = nearbyBoids.map(_.velocity).fold(Vector[Float](0, 0))(sum(_, _)).map(_ / nearbyBoids.size)
      val steeringDirection = sum(sum(separationVector, cohesionVector), alignmentVector)
      val steeringForce = truncate(steeringDirection, maxForce)
      val acceleration = steeringForce.map(_ / mass)
      val newVelocity = truncate(sum(velocity, acceleration), maxSpeed)
      val newPosition = sum(position, newVelocity).map(p => if (p < 0) (p + 500) % 500 else p % 500)
      println("Separation: " + separationVector.mkString("(", ", ", ")"))
      println("Cohesion: " + cohesionVector.mkString("(", ", ", ")"))
      println("Alignment: " + alignmentVector.mkString("(", ", ", ")"))
      new Boid(newPosition, newVelocity)
    } else {
      new Boid(sum(position, velocity).map(p => if (p < 0) (p + 500) % 500 else p % 500), velocity) //copy paste from row 26 BAD
    }
  }
}