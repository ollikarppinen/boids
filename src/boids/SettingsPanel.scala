package boids

import scala.swing.{ BoxPanel, Orientation, Slider, Button, Label }
import java.awt.{ Graphics2D, Color, Dimension, Font, Component }
import javax.swing.{ UIManager }

object SettingsPanel extends BoxPanel(Orientation.Vertical) {

	val preferredFont            = new Font("Bauhaus 93", Font.PLAIN, 16)
	val preferredBackgroundColor = new Color(237, 237, 237)
	val preferredForegroundColor = new Color(100, 100, 100)

  background    = preferredBackgroundColor
  preferredSize = new Dimension(300, 1000)
  
  UIManager.put("Slider.background" , preferredBackgroundColor)
  UIManager.put("Slider.orientation",   Orientation.Horizontal)
  UIManager.put("Slider.foreground" , preferredForegroundColor)

  UIManager.put("Button.background" , preferredBackgroundColor)
  UIManager.put("Button.font"       ,            preferredFont)
  UIManager.put("Button.foreground" , preferredForegroundColor)

  UIManager.put("Label.font"      ,              preferredFont)
  UIManager.put("Label.foreground",   preferredForegroundColor)
  
  val pauseButton   = new Button("Pause")
	val restartButton = new Button("Restart")
  val resetButton   = new Button("Reset")
  val randomButton  = new Button("Random")
  val massSlider = new Slider {
    min   =   1
    max   = 100
    value =   1
  }
  val distanceSlider = new Slider {
    min   =   1
    max   = 200
    value =  10
  }
  val maxForceSlider = new Slider {
    min   =   1
    max   = 100
    value =  10
  }
  val maxSpeedSlider = new Slider {
    min   =   1
    max   = 100
    value =  10
  }
  val flockSizeSlider = new Slider {
    min   =    0
    max   = 1000
    value =  200
  }
  val separationSlider = new Slider {
    min   =    1
    max   =  100
    value =   10
  }
  val cohesionSlider = new Slider {
    min   =    1
    max   =  100
    value =   10
  }
  val alignmentSlider = new Slider {
    min   =    1
    max   =  100
    value =   10
  }
  contents +=
    (new Label(""), 
     new Label("Weight"), massSlider,
     new Label("Perception distance"), distanceSlider,
     new Label("Surroundings influence"), maxForceSlider,
     new Label("Speed"),  maxSpeedSlider,
     new Label("#"), flockSizeSlider,
     new Label("Separation"), separationSlider,
     new Label("Cohesion"), cohesionSlider,
     new Label("Alignment"), alignmentSlider,
     pauseButton, restartButton, resetButton, randomButton)
}