package boids

import scala.swing.{ BoxPanel, Orientation, Slider, Button, Label }
import java.awt.{ Graphics2D, Color, Dimension, Font, Component }
import javax.swing.{ UIManager, Box }

object SettingsPanel extends BoxPanel(Orientation.Vertical) { // The settings panel which contains all the sliders and buttons.
  val preferredFont = new Font("Miriam Fixed", Font.PLAIN, 16)
  val preferredBackgroundColor = new Color(237, 237, 237)
  val preferredForegroundColor = new Color(100, 100, 100)
  background = preferredBackgroundColor
  preferredSize = new Dimension(300, 1000)
  
//  These set the Swing object default settings to desired state so that each object doesn't have to be adjusted individually
  UIManager.put("Slider.background", preferredBackgroundColor)
  UIManager.put("Slider.orientation", Orientation.Horizontal)
  UIManager.put("Slider.foreground", preferredForegroundColor)
  UIManager.put("Button.background", preferredBackgroundColor)
  UIManager.put("Button.font", preferredFont)
  UIManager.put("Button.foreground", preferredForegroundColor)
  UIManager.put("Label.font", preferredFont)
  UIManager.put("Label.foreground", preferredForegroundColor)
  val pauseButton = new Button("Pause")
  val restartButton = new Button("Restart")
  val resetButton = new Button("Reset")
  val randomButton = new Button("Random")
  val massSlider = new Slider {      // Adjusts boids mass. Changes how quickly boids turn.
    min = 1
    max = 100
    value = 1
  }
  val distanceSlider = new Slider {  //  Adjusts the distance which boids use to decide if another boid affects its movement. This setting has big effect on performance.
    min = 1
    max = 200
    value = 10
  }
  val maxForceSlider = new Slider {  //  Adjusts how much rule vectors affect to a boid.
    min = 1
    max = 100
    value = 10
  }
  val maxSpeedSlider = new Slider {  //  Adjusts the movement speed of boids.
    min = 1
    max = 100
    value = 10
  }
  val flockSizeSlider = new Slider { //  Adjusts the number of boids in the flock. Heavy performance effect.
    min = 0
    max = 1000
    value = 200
  }
  val separationSlider = new Slider {// Adjusts how much boids avoid each others.
    min = 0
    max = 100
    value = 10
  }
  val cohesionSlider = new Slider {  // Adjusts how much boids try to group together.
    min = 0
    max = 100
    value = 10
  }
  val alignmentSlider = new Slider { // Adjusts how much boids try to face the same direction.
    min = 0
    max = 100
    value = 10
  }
  
//  Objects are added to the panel and empty space is added between each object. Empty space is added with vertical struts.
  peer.add(Box.createVerticalStrut(30)) 
  contents += (new Label("Weight"), massSlider)  
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("Perception distance"), distanceSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("Surroundings influence"), maxForceSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("Speed"), maxSpeedSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("#"), flockSizeSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("Separation"), separationSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("Cohesion"), cohesionSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (new Label("Alignment"), alignmentSlider)
  peer.add(Box.createVerticalStrut(30))
  contents += (pauseButton)
  peer.add(Box.createVerticalStrut(20))
  contents += (restartButton)
  peer.add(Box.createVerticalStrut(20))
  contents += (randomButton)
  peer.add(Box.createVerticalStrut(20))
  contents += (resetButton)
}