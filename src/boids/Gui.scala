package boids

import scala.swing. { SimpleSwingApplication, MainFrame, BorderPanel, Button, Slider }
import scala.swing.event.{ ButtonClicked, ValueChanged}

object Gui extends SimpleSwingApplication {
  val top = new MainFrame {
    title = "Boid simulation"
    resizable = false
    
    contents = new BorderPanel {
      layout(SimulationPanel) = BorderPanel.Position.Center
      layout(SettingsPanel) = BorderPanel.Position.East
    }
    peer.setLocationRelativeTo(null)
  }
  listenTo(SettingsPanel.massSlider,
           SettingsPanel.pauseButton,
           SettingsPanel.restartButton,
           SettingsPanel.distanceSlider,
           SettingsPanel.maxForceSlider,
           SettingsPanel.maxSpeedSlider,
           SettingsPanel.flockSizeSlider)
  reactions += {
    case ButtonClicked(source) => {
      val button = source.asInstanceOf[Button]
      button match {
        case SettingsPanel.pauseButton   => SimulationPanel.pause()
        case SettingsPanel.restartButton => SimulationPanel.restart()
      }
    }
    case ValueChanged(source) => {
      val slider = source.asInstanceOf[Slider]
        slider match {
          case SettingsPanel.massSlider      => Boid.mass       = slider.value
          case SettingsPanel.distanceSlider  => Boid.distance   = slider.value * slider.value
          case SettingsPanel.maxForceSlider  => Boid.maxForce   = slider.value
          case SettingsPanel.maxSpeedSlider  => Boid.maxSpeed   = 0.1f * slider.value
          case SettingsPanel.flockSizeSlider => Simulation.size = slider.value
        }
    }
  }
}