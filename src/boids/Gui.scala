package boids

import scala.swing._
import scala.swing.BorderPanel.Position._
import java.awt.Color

object Gui extends SimpleSwingApplication{
  val top = new MainFrame {
    title = "Boid simulation"
    background = Color.red
    contents = new BorderPanel {
      layout(SimulationPanel) = Center
      layout(SettingsPanel) = East
    }
  }
  
}

