package boids

import scala.util.Random

object simulation extends Runnable {
	val r = new Random
	var flock = Vector.fill[Boid](100)(new Boid(Vector(r.nextInt(501), r.nextInt(501)), Vector( r.nextInt(3) + r.nextFloat() - 2,  r.nextInt(3) + r.nextFloat() - 2))).par
  def run(): Unit = {
    
    var end = System.currentTimeMillis()
    var lag = 0.0
    while (true) { // This is the simulation loop
//      val start = System.currentTimeMillis()
//      val elapsed = start - end
//      end = start
//      lag += elapsed
//      while (lag >= 20) {
    	  flock = flock.map(_.move)     
//        lag -= 20
//      }
      SimulationPanel.repaint()
//      Thread.sleep(start + 15 - System.currentTimeMillis()) 
    }
  }

}