package boids

import org.junit.Test
import org.junit.Assert._
import scala.util.Random
import Vector2D._

class UTests {
  @Test def testVectorAddition {
    assertTrue("testVectorAddition() failed", new Vector2D(0, 1) + new Vector2D(1, 0) == Vector2D(1, 1))
  }
  @Test def testVectorSubstract {
    assertTrue("testVectorSubstract() failed", new Vector2D(1, 1) - new Vector2D(1, 0) == Vector2D(0, 1))
  }
  @Test def testVectorMultiplication {
    assertTrue("testVectorMultiplication() failed", new Vector2D(1, 2) * 3 == Vector2D(3, 6))
  }
  @Test def testVectorLengthComparison {
    assertTrue("testVectorLengthComparison() failed", new Vector2D(1, 0) < 2) 
  }
  @Test def testVectorSquare {
    assertTrue("testVectorSquare() failed", new Vector2D(1, 2).sqr == 5)
  }  
  @Test def testVectorNormalization {
    assertTrue("testVectorNormalization() failed", new Vector2D(2, 2).normalize < 1.05f)
  }
  @Test def testVectorNormalization2 {
    assertTrue("testVectorNormalization2() failed", !(new Vector2D(2, 2).normalize < 0.95f))
  }
}