package boids

import scala.swing._
import java.awt.{ Graphics2D, Color, Dimension }

object SettingsPanel extends FlowPanel {
  background = Color.lightGray
  preferredSize = new Dimension(100, 500)
  contents += new Label {
    text = "Settings"
  }
}