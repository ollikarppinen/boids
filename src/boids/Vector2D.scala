package boids

case class Vector2D(x: Float, y: Float) {

  val length = math.hypot(x, y)
  val angle  = math.atan2(y, x)

  def + (v: Vector2D) = Vector2D(x + v.x, y + v.y)
  def * (f: Float) = Vector2D(x * f, y * f)
  def - (v: Vector2D) = Vector2D(x - v.x, y - v.y)
  
  def normalize(v: Vector2D) = v * fastInvSqrt(x * x + y * y)
    
  def fastInvSqrt(x: Float) = { // taken straight from the wikipedia
    val i = java.lang.Float.intBitsToFloat(0x5f3759df - (java.lang.Float.floatToIntBits(x) >> 1))
    i * (1.5f - 0.5f * x * i * i)
  }
  
}