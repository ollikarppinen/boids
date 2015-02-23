package boids

import scala.swing._
import scala.swing.BorderPanel.Position._
import java.awt.Color

object Gui extends SimpleSwingApplication{
  val top = new MainFrame {
    title = "Boid simulation"
    resizable = false
    contents = new BorderPanel {
      layout(SimulationPanel) = Center
//      layout(SettingsPanel) = East
    }
  }
}