package boids

import scala.swing._
import java.awt.{ Graphics2D, Color, Dimension }

object SettingsPanel extends FlowPanel {
  background = Color.white
  preferredSize = new Dimension(200, 1000)
  
  val massSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 100
    value = 10
    background = Color.white
    minimumSize = new Dimension(200, 300)
  }

  val distanceSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 200
    value = 10
    background = Color.white
    minimumSize = new Dimension(200, 300)
  }
  
  val maxForceSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 100
    value = 10
    background = Color.white
    minimumSize = new Dimension(200, 300)
  }
  
  val maxSpeedSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 1
    max = 100
    value = 10
    background = Color.white
    minimumSize = new Dimension(200, 300)
  }
  
  val flockSizeSlider = new Slider {
    orientation = Orientation.Horizontal
    min = 0
    max = 1000
    value = 200
    background = Color.white
    minimumSize = new Dimension(200, 300)
  }
  
  val pauseButton = new Button {
    text = "Pause"
    preferredSize = new Dimension(180, 30)
    background = Color.white
  }
  
  val restartButton = new Button {
    text = "Restart"
    preferredSize = new Dimension(180, 30)
    background = Color.white
  }
  
  contents += (new Label("Settings"), 
               massSlider, new Label("Mass"), 
               distanceSlider, new Label("Distance"), 
               maxForceSlider, new Label("Max Force"), 
               maxSpeedSlider, new Label("Max Speed"), 
               flockSizeSlider, new Label("Number of boids"),
               pauseButton, restartButton)
}