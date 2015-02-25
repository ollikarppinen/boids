package boids

import scala.swing.{ BoxPanel, Orientation, Slider, Button, Label }
import java.awt.{ Graphics2D, Color, Dimension, Font }
import javax.swing.UIManager

object SettingsPanel extends BoxPanel(Orientation.Vertical) {

	val preferredFont            = new Font("Bauhaus 93", Font.PLAIN, 16)
	val preferredBackgroundColor = new Color(147, 147, 147)
	val preferredForegroundColor = Color.darkGray

  background    = preferredBackgroundColor
  preferredSize = new Dimension(300, 1000)
  
  UIManager.put("Slider.background" , preferredBackgroundColor)
  UIManager.put("Slider.minimumSize",  new Dimension(150, 300))
  UIManager.put("Slider.orientation",   Orientation.Horizontal)
  UIManager.put("Slider.foreground" , preferredForegroundColor)

  UIManager.put("Button.background" , preferredBackgroundColor)
  UIManager.put("Button.minimumSize",   new Dimension(150, 50))
  UIManager.put("Button.font"       ,            preferredFont)
  UIManager.put("Button.foreground" , preferredForegroundColor)

  UIManager.put("Label.font"      ,              preferredFont)
  UIManager.put("Label.foreground",   preferredForegroundColor)

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
    min   =   0
    max   = 100
    value =  10
  }

  val flockSizeSlider = new Slider {
    min   =    0
    max   = 1000
    value =  200
  }

  val pauseButton   = new Button("Pause")
  val restartButton = new Button("Restart")

  contents +=
    (new Label("Mass")           ,      massSlider,
     new Label("Distance")       ,  distanceSlider,
     new Label("Max Force")      ,  maxForceSlider,
     new Label("Max Speed")      ,  maxSpeedSlider,
     new Label("Number of boids"), flockSizeSlider,
     pauseButton,
     restartButton)
}