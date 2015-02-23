package boids

import scala.swing._
import scala.swing.BorderPanel.Position._

object Gui extends SimpleSwingApplication {
  val top = new MainFrame {
    title = "Boid simulation"
    resizable = true
    contents = new BorderPanel {
      layout(SimulationPanel) = Center
    }
  }
}