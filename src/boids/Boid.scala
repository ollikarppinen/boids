package boids

object Boid {                  // Companion object to boid class that contains the simulation settings that the boids follow
var mass = 1                   // Mass of one boid
  var distance = 100           // Max distance squared that boids affect each others
  var maxForce = 10f           // Max length for the acceleration vector
  var maxSpeed = 1f            // Max speed that boid moves in one simulation step
  var separationWeight = 1f    // Rule weight values
  var cohesionWeight = 1f
  var alignmentWeight = 1f
  val r = scala.util.Random
  def doBoid: Boid = {         // Returns new boid with random direction and random location
    new Boid(Vector2D(r.nextInt(501),
      r.nextInt(501)),
      Vector2D(r.nextInt(2) + r.nextFloat() - 1, r.nextInt(2) + r.nextFloat() - 1))
  }
}

class Boid(val position: Vector2D, val velocity: Vector2D) {  // Representation of a singular boid
  def move: Boid = {                                          // Method that moves this specific boid obeying the rules of the simulation. Returns the new boid, which has updated position and velocity vector.
    val nearbyBoids = Simulation.flock.filter(b => this.position - this.position.transfix(b.position) < Boid.distance) // Finds the nearby boids that are closer than Boid.distance to this boid.
//    Separation, cohesion and alignment values are the rule vectors which affect to the movement of a boid.
    val separation = nearbyBoids.map(b => (this.position - this.position.transfix(b.position)) * this.position.inverseDistance(b.position)) // Rule vector that keeps boids from colliding
                                .reduce(_ + _) * Boid.separationWeight
    val cohesion = (nearbyBoids.map(b => (this.position.transfix(b.position) / nearbyBoids.size))  // Rule vector that makes boids group together
                               .reduce(_ + _) - this.position) * Boid.cohesionWeight
    val alignment = nearbyBoids.map(_.velocity / nearbyBoids.size)  // Rule vector that turns boids to phase same direction
                               .reduce(_ + _) * Boid.alignmentWeight
    val acceleration = (separation + cohesion + alignment).truncate(Boid.maxForce) / Boid.mass  // Rule vectors are added and divided by the mass of a boid to create fluid motion. Changes in motion is affected by the mass of a boid.
    val newVelocity = (velocity + acceleration).truncate(Boid.maxSpeed)  // This is boids new velocity vector; a vector with direction and magnitude. It is capped by max speed and it's a combination of the acceleration vector and the old velocity vector.
    val newPosition = (position + newVelocity).bound  // New position is calculated by adding new velocity vector to old position. ".bound" ensures that boids don't go outside of the grid.
    new Boid(newPosition, newVelocity)
  }
}