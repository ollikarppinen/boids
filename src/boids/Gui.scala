package boids

import scala.swing._
import scala.swing.BorderPanel.Position._
import scala.swing.event.ButtonClicked
import scala.swing.event.ValueChanged

object Gui extends SimpleSwingApplication {
  val top = new MainFrame {
    title = "Boid simulation"
    resizable = true
    contents = new BorderPanel {
      layout(SimulationPanel) = Center
      layout(SettingsPanel) = East
    }
  }
  var running = true
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
        case SettingsPanel.restartButton => Simulation.restart()
      }
    }
    case ValueChanged(source) => {
      val slider = source.asInstanceOf[Slider]
        slider match {
          case SettingsPanel.massSlider      => Boid.mass       = slider.value
          case SettingsPanel.distanceSlider  => Boid.distance   = slider.value * slider.value
          case SettingsPanel.maxForceSlider  => Boid.maxForce   = slider.value
          case SettingsPanel.maxSpeedSlider  => Boid.maxSpeed   = slider.value
          case SettingsPanel.flockSizeSlider => Simulation.size = slider.value
        }
    }
  }
}