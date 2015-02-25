package boids

case class Vector2D(x: Float, y: Float) {

  def +(v: Vector2D) = Vector2D(x + v.x, y + v.y)
  def -(v: Vector2D) = Vector2D(x - v.x, y - v.y)
  def *(f: Float) = Vector2D(x * f, y * f)
  def /(f: Float) = this * (1f / f)
  def <(f: Float) = sqr < f // Compares vector length to f. Returns true if vector is shorter.

  lazy val cs = math.cos(90.toRadians).toFloat
  lazy val sn = math.sin(90.toRadians).toFloat
  lazy val sqr = x * x + y * y // Returns square of the coordinates

  def normalize = this * fastInvSqrt(sqr) // Returns normalized vector. Ie. Vector that has length 1 and unchanged direction.
  def inverseDistance(v: Vector2D) = fastInvSqrt((this - transfix(v)).sqr) // Returns 1/d, where d = (x^2 + y^2)^(1/2)
  def truncate(f: Float) = if (this.sqr > f * f) this.normalize * f else this // Returns shortened vector with length f or the original.
  def rotate =  Vector2D(x * cs - y * sn, x * sn +   y * cs)// Returns a vector that has been rotated 90 degrees clockwise
  def bound = Vector2D(if (x < 0) x + 500 else x % 500, if (y < 0) y + 500 else y % 500) // Keeps coordinates on the grid. Rolling coordinates.
  def transfix(v: Vector2D) = Vector2D(if (x - v.x > 250) v.x + 500 else if (x - v.x < -250) v.x - 500 else v.x, // Returns transfixed coordinates
		  if (y - v.y > 250) v.y + 500 else if (y - v.y < -250) v.y - 500 else v.y) // that are closest
  def fastInvSqrt(x: Float) = { // Return approximation of 1/(x^(1/2)). Source: Fast inverse square root from Wikipedia.
    val i = java.lang.Float.intBitsToFloat(0x5f3759df - (java.lang.Float.floatToIntBits(x) >> 1))
    i * (1.5f - 0.5f * x * i * i)
  }
}