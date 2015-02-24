package boids

import scala.swing._
import java.awt.{ Graphics2D, Color, Dimension }

object SettingsPanel extends FlowPanel {
  background = Color.white
  
  preferredSize = new Dimension(200, 1000)
  contents += new Label {
    text = "Settings"
  }
  
  val massSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 100
    value = 10
    minimumSize = new Dimension(200, 300)
  }
  contents += massSlider
  contents += new Label("Mass")
  
  val distanceSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 200
    value = 10
    majorTickSpacing = 10
    paintTicks = true
    minimumSize = new Dimension(200, 300)
  }
  contents += distanceSlider
  contents += new Label("Distance")
  
  val maxForceSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 100
    value = 10
    majorTickSpacing = 10
    paintTicks = true
    minimumSize = new Dimension(200, 300)
  }
  contents += maxForceSlider
  contents += new Label("Max Force")
  
  val maxSpeedSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 100
    value = 10
    majorTickSpacing = 10
    paintTicks = true
    minimumSize = new Dimension(200, 300)
  }
  contents += maxSpeedSlider
  contents += new Label("Max Speed")
  
  val flockSizeSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 0
    max = 1000
    value = 200
    minimumSize = new Dimension(200, 300)
  }
  contents += flockSizeSlider
  contents += new Label("Number of boids")
  
  val pauseButton = new Button {
    text = "Pause"
    preferredSize = new Dimension(180, 30)
  }
  contents += pauseButton
  
  val restartButton = new Button {
    text = "Restart"
    preferredSize = new Dimension(180, 30)
  }
  contents += restartButton
}