package boids

import scala.swing._
import java.awt.{ Graphics2D, Color, Dimension }

object SettingsPanel extends FlowPanel {
  background = Color.black
  preferredSize = new Dimension(200, 1000)
  contents += new Label {
    text = "Settings"
  }
}