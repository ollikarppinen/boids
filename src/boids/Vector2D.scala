package boids

case class Vector2D(x: Float, y: Float) {

  def + (v: Vector2D) = Vector2D(x + v.x, y + v.y)
  def * (f: Float) = Vector2D(x * f, y * f)
  def - (v: Vector2D) = Vector2D(x - v.x, y - v.y)
  def < (f: Float) = sqr < f
  
  lazy val sqr = x * x + y * y
  def normalize = this * fastInvSqrt(x * x + y * y)
  def inverseDistance(v: Vector2D) = fastInvSqrt((this - v).sqr)
  def truncate(f: Float) = if (!(this < f * f)) this.normalize * f else this
    
  def fastInvSqrt(x: Float) = { // taken straight from the wikipedia
    val i = java.lang.Float.intBitsToFloat(0x5f3759df - (java.lang.Float.floatToIntBits(x) >> 1))
    i * (1.5f - 0.5f * x * i * i)
  }
  
  def b(f: Float) = if (f < 0) (f + 500) else f % 500
  def bound = Vector2D(b(x), b(y))
  override def toString = "Vector(" + x + ", " + y + ")"
  
}