package boids

import scala.util.Random

class Boid(var position: Vector[Int], val velocity: Vector[Double]) {
  val r = new Random
  def getX = position(0)
  def getY = position(1)
  def move() = {
    this.position = Vector((this.position(0) + r.nextInt(4) - 1) % 500, (this.position(1) + r.nextInt(4) - 1) % 500)
  }
}